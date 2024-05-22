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

    fetchProdutos();
    fetchFornecedores();
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

  return (
    <div className="transacao-page" style={{ backgroundImage: `url(${gasBackground})` }}>
      <div className="transacao-form">
        <h2>Cadastrar Nova Transação</h2>
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
    </div>
  );
}

export default TransacaoPage;