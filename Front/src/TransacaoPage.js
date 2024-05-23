import React, { useState, useEffect } from 'react';
import { Form, Button, Alert } from 'react-bootstrap';
import axios from 'axios';
import './Transacaopage.css';
import gasBackground from './Imagens/gas.jpg';

function TransacaoPage() {
  const [produtoId, setProdutoId] = useState('');
  const [quantidadeEntrada, setQuantidadeEntrada] = useState(0);
  const [quantidadeSaida, setQuantidadeSaida] = useState(0);
  const [success, setSuccess] = useState('');
  const [produtos, setProdutos] = useState([]);
  const [fornecedores, setFornecedores] = useState([]);
  const [selectedFornecedorId, setSelectedFornecedorId] = useState('');
  const [volumeEstoque, setVolumeEstoque] = useState(0); 
  const [estoqueAtual, setEstoqueAtual] = useState(''); // Novo estado para o estoque atual

  useEffect(() => {
    const fetchProdutos = async () => {
      try {
        const response = await axios.get('http://localhost:8080/produtos');
        setProdutos(response.data);
      } catch (error) {
        console.error('Erro ao buscar produtos:', error);
      }
    };

    const fetchFornecedores = async () => {
      try {
        const response = await axios.get('http://localhost:8080/fornecedores');
        setFornecedores(response.data);
      } catch (error) {
        console.error('Erro ao buscar fornecedores:', error);
      }
    };

    // Buscar volume de itens no estoque
    const fetchVolumeEstoque = async () => {
      try {
        const response = await axios.get('http://localhost:8080/estoques/volume');
        setVolumeEstoque(response.data.volume);
      } catch (error) {
        console.error('Erro ao buscar volume de estoque:', error);
      }
    };

    fetchProdutos();
    fetchFornecedores();
    fetchVolumeEstoque(); 
  }, []);

  const handleProdutoIdChange = (event) => {
    setProdutoId(event.target.value);
  };

  const handleQuantidadeEntradaChange = (event) => {
    setQuantidadeEntrada(parseInt(event.target.value));
  };

  const handleQuantidadeSaidaChange = (event) => {
    setQuantidadeSaida(parseInt(event.target.value));
  };

  const handleFornecedorChange = (event) => {
    setSelectedFornecedorId(event.target.value);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      await axios.post('http://localhost:8080/estoques', {
        produto: { id: produtoId },
        entrada: quantidadeEntrada,
        saida: quantidadeSaida
      });
      setSuccess('Transação cadastrada com sucesso!');
      // Limpar os campos após o envio
      setProdutoId('');
      setQuantidadeEntrada(0);
      setQuantidadeSaida(0);
      setSelectedFornecedorId('');
    } catch (error) {
      console.error('Erro ao cadastrar transação:', error);
    }
  };

  // Função para buscar o estoque atual de um produto
  const handleBuscarEstoqueAtual = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/estoques/estoqueAtual?produtoId=${produtoId}`);
      setEstoqueAtual(response.data.estoqueAtual);
    } catch (error) {
      console.error('Erro ao buscar estoque atual:', error);
    }
  };

  return (
    <div className="transacao-page" style={{ backgroundImage: `url(${gasBackground})` }}>
      <div className="transacao-form">
        <h2>Cadastrar Nova Transação</h2>
        <p>Volume de Itens no Estoque: {volumeEstoque}</p> 
        <Form onSubmit={handleSubmit}>
          <Form.Group controlId="fornecedorId">
            <Form.Label>Fornecedor</Form.Label>
            <Form.Control as="select" value={selectedFornecedorId} onChange={handleFornecedorChange}>
              <option value="">Selecione um fornecedor</option>
              {fornecedores.map(fornecedor => (
                <option key={fornecedor.id} value={fornecedor.id}>{fornecedor.nome}</option>
              ))}
            </Form.Control>
          </Form.Group>
          <Form.Group controlId="produtoId">
            <Form.Label>Produto</Form.Label>
            <Form.Control as="select" value={produtoId} onChange={handleProdutoIdChange}>
              <option value="">Selecione um produto</option>
              {produtos.map(produto => (
                <option key={produto.id} value={produto.id}>{produto.nome}</option>
              ))}
            </Form.Control>
          </Form.Group>
          <Form.Group controlId="quantidadeEntrada">
            <Form.Label>Quantidade de Entrada</Form.Label>
            <Form.Control type="number" placeholder="Digite a quantidade de entrada" value={quantidadeEntrada} onChange={handleQuantidadeEntradaChange} />
          </Form.Group>
          <Form.Group controlId="quantidadeSaida">
            <Form.Label>Quantidade de Venda</Form.Label>
            <Form.Control type="number" placeholder="Digite a quantidade de saída" value={quantidadeSaida} onChange={handleQuantidadeSaidaChange} />
          </Form.Group>
          <Button variant="primary" type="submit">
            Cadastrar Transação
          </Button>
        </Form>
        {success && <Alert variant="success" className="mt-3">{success}</Alert>}
      </div>

      {/* Formulário para exibir o estoque atual */}
      <div className="estoque-form">
        <h2>Estoque Atual</h2>
        <Form.Group controlId="buscarEstoque">
          <Form.Label>Buscar Estoque por Produto</Form.Label>
          <Form.Control as="select" value={produtoId} onChange={handleProdutoIdChange}>
            <option value="">Selecione um produto</option>
            {produtos.map(produto => (
              <option key={produto.id} value={produto.id}>{produto.nome}</option>
            ))}
          </Form.Control>
          <Button variant="primary" onClick={handleBuscarEstoqueAtual}>
            Buscar
          </Button>
        </Form.Group>
        <Form.Group>
          <Form.Label>Estoque Atual:</Form.Label>
          <Form.Control type="text" readOnly value={estoqueAtual} />
        </Form.Group>
      </div>
    </div>
  );
}

export default TransacaoPage;
