import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import './Transacaopage.css'; 
import gasBackground from './Imagens/gas.jpg'; 

function TransacaoPage() {
  const [produtoId, setProdutoId] = useState('');
  const [quantidadeEntrada, setQuantidadeEntrada] = useState(0);
  const [quantidadeSaida, setQuantidadeSaida] = useState(0);

  const handleProdutoIdChange = (event) => {
    setProdutoId(event.target.value);
  };

  const handleQuantidadeEntradaChange = (event) => {
    setQuantidadeEntrada(parseInt(event.target.value));
  };

  const handleQuantidadeSaidaChange = (event) => {
    setQuantidadeSaida(parseInt(event.target.value));
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    // Aqui você pode enviar os dados para a API e processar a transação
    console.log('Dados da transação:', { produtoId, quantidadeEntrada, quantidadeSaida });
  };

  return (
    <div className="transacao-page" style={{ backgroundImage: `url(${gasBackground})` }}>
      <div className="transacao-form">
        <h2>Cadastrar Nova Transação</h2>
        <Form onSubmit={handleSubmit}>
          <Form.Group controlId="produtoId">
            <Form.Label>ID do Produto</Form.Label>
            <Form.Control type="text" placeholder="Digite o ID do produto" value={produtoId} onChange={handleProdutoIdChange} />
          </Form.Group>
          <Form.Group controlId="quantidadeEntrada">
            <Form.Label>Quantidade de Entrada</Form.Label>
            <Form.Control type="number" placeholder="Digite a quantidade de entrada" value={quantidadeEntrada} onChange={handleQuantidadeEntradaChange} />
          </Form.Group>
          <Form.Group controlId="quantidadeSaida">
            <Form.Label>Quantidade de Saída</Form.Label>
            <Form.Control type="number" placeholder="Digite a quantidade de saída" value={quantidadeSaida} onChange={handleQuantidadeSaidaChange} />
          </Form.Group>
          <Button variant="primary" type="submit">
            Cadastrar Transação
          </Button>
        </Form>
      </div>
    </div>
  );
}

export default TransacaoPage;

