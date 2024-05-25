import React, { useState } from "react";
import { Form, Button, Alert } from "react-bootstrap";
import "./Transacaopage.css";
import gasBackground from "./Imagens/gas.jpg";

function TransacaoPage() {
  const [produtoId, setProdutoId] = useState("");
  const [quantidadeEntrada, setQuantidadeEntrada] = useState(0);
  const [quantidadeSaida, setQuantidadeSaida] = useState(0);
  const [success, setSuccess] = useState("");
  const [estoque, setEstoque] = useState({});
  const [estoqueAtual, setEstoqueAtual] = useState(0);

  // Lista de produtos fixa
  const produtos = [
    { id: "p13", nome: "Produto 13" },
    { id: "p20", nome: "Produto 20" },
    { id: "p45", nome: "Produto 45" },
  ];

  const handleProdutoIdChange = (event) => {
    const selectedId = event.target.value;
    setProdutoId(selectedId);
    setEstoqueAtual(estoque[selectedId] || 0);
  };

  const handleQuantidadeEntradaChange = (event) => {
    setQuantidadeEntrada(parseInt(event.target.value, 10));
  };

  const handleQuantidadeSaidaChange = (event) => {
    setQuantidadeSaida(parseInt(event.target.value, 10));
  };

  const handleCadastrarEntrada = () => {
    setEstoque((prevEstoque) => {
      const novaQuantidade = (prevEstoque[produtoId] || 0) + quantidadeEntrada;
      setEstoqueAtual(novaQuantidade);
      return { ...prevEstoque, [produtoId]: novaQuantidade };
    });
    setSuccess("Entrada cadastrada com sucesso!");
    setQuantidadeEntrada(0);
  };

  const handleCadastrarSaida = () => {
    setEstoque((prevEstoque) => {
      const novaQuantidade = (prevEstoque[produtoId] || 0) - quantidadeSaida;
      setEstoqueAtual(novaQuantidade);
      return { ...prevEstoque, [produtoId]: novaQuantidade };
    });
    setSuccess("Saída cadastrada com sucesso!");
    setQuantidadeSaida(0);
  };

  return (
    <div
      className="transacao-page"
      style={{ backgroundImage: `url(${gasBackground})` }}
    >
      <div className="transacao-form">
        <h2>Cadastrar Nova Transação</h2>
        <Form.Group controlId="produtoId">
          <Form.Label>Produto</Form.Label>
          <Form.Control
            as="select"
            value={produtoId}
            onChange={handleProdutoIdChange}
          >
            <option value="">Selecione um produto</option>
            {produtos.map((produto) => (
              <option key={produto.id} value={produto.id}>
                {produto.nome}
              </option>
            ))}
          </Form.Control>
        </Form.Group>
        <Form.Group controlId="quantidadeEntrada">
          <Form.Label>Quantidade de Entrada</Form.Label>
          <Form.Control
            type="number"
            placeholder="Digite a quantidade de entrada"
            value={quantidadeEntrada}
            onChange={handleQuantidadeEntradaChange}
          />
          <Button variant="primary" onClick={handleCadastrarEntrada} className="mt-2">
            Cadastrar Entrada
          </Button>
        </Form.Group>
        <Form.Group controlId="quantidadeSaida">
          <Form.Label>Quantidade de Venda</Form.Label>
          <Form.Control
            type="number"
            placeholder="Digite a quantidade de saída"
            value={quantidadeSaida}
            onChange={handleQuantidadeSaidaChange}
          />
          <Button variant="primary" onClick={handleCadastrarSaida} className="mt-2">
            Cadastrar Saída
          </Button>
        </Form.Group>
        {success && (
          <Alert variant="success" className="mt-3">
            {success}
          </Alert>
        )}
      </div>

      <div className="estoque-form">
        <h2>Estoque Atual</h2>
        <Form.Group controlId="buscarEstoque">
          <Form.Label>Produto Selecionado:</Form.Label>
          <Form.Control
            as="select"
            value={produtoId}
            onChange={handleProdutoIdChange}
          >
            <option value="">Selecione um produto</option>
            {produtos.map((produto) => (
              <option key={produto.id} value={produto.id}>
                {produto.nome}
              </option>
            ))}
          </Form.Control>
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
