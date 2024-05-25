import React, { useState } from "react";
import axios from "axios";
import "./Cadastro.css";

axios.interceptors.request.use((config) => {
  config.headers.Authorization = localStorage.getItem("token");

  return config;
});

function Cadastro() {
  const [fornecedor, setFornecedor] = useState({
    nome: "",
    endereco: "",
    telefone: "",
  });

  const [produto, setProduto] = useState({
    nome: "",
    fornecedorId: "",
  });

  const handleFornecedorChange = (e) => {
    const { name, value } = e.target;
    setFornecedor({ ...fornecedor, [name]: value });
  };

  const handleProdutoChange = (e) => {
    const { name, value } = e.target;
    setProduto({ ...produto, [name]: value });
  };

  const handleSubmitFornecedor = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8080/fornecedores",
        fornecedor
      );
      const fornecedorId = response.data.id;
      alert(`Fornecedor cadastrado com sucesso! ID: ${fornecedorId}`);
      setFornecedor({
        nome: "",
        endereco: "",
        telefone: "",
      });
    } catch (error) {
      console.error("Erro ao cadastrar fornecedor:", error);
      alert(
        "Erro ao cadastrar fornecedor. Verifique o console para mais detalhes."
      );
    }
  };

  const handleSubmitProduto = async (e) => {
    e.preventDefault();
    try {
      if (isNaN(produto.fornecedorId)) {
        alert("O ID do fornecedor deve ser um número.");
        return;
      }
      const produtoPayload = {
        nome: produto.nome,
        fornecedor: { id: produto.fornecedorId },
      };
      const response = await axios.post(
        "http://localhost:8080/produtos",
        produtoPayload
      );
      alert("Produto cadastrado com sucesso!");
      setProduto({
        nome: "",
        fornecedorId: "",
      });
    } catch (error) {
      console.error("Erro ao cadastrar produto:", error);
      if (error.response) {
        console.error("Detalhes do erro:", error.response.data);
      }
      alert(
        "Erro ao cadastrar produto. Verifique o console para mais detalhes."
      );
    }
  };

  return (
    <div className="container">
      <div className="form-container">
        <h2>Cadastro de Fornecedor</h2>
        <form onSubmit={handleSubmitFornecedor}>
          <div className="form-group">
            <label htmlFor="nomeFornecedor">Nome do Fornecedor:</label>
            <input
              type="text"
              id="nomeFornecedor"
              name="nome"
              value={fornecedor.nome}
              onChange={handleFornecedorChange}
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="enderecoFornecedor">Endereço:</label>
            <input
              type="text"
              id="enderecoFornecedor"
              name="endereco"
              value={fornecedor.endereco}
              onChange={handleFornecedorChange}
            />
          </div>
          <div className="form-group">
            <label htmlFor="telefoneFornecedor">Telefone:</label>
            <input
              type="text"
              id="telefoneFornecedor"
              name="telefone"
              value={fornecedor.telefone}
              onChange={handleFornecedorChange}
              required
            />
          </div>
          <button type="submit">Cadastrar Fornecedor</button>
        </form>
      </div>

      <div className="form-container">
        <h2>Cadastro de Produto</h2>
        <form onSubmit={handleSubmitProduto}>
          <div className="form-group">
            <label htmlFor="nomeProduto">Nome do Produto:</label>
            <input
              type="text"
              id="nomeProduto"
              name="nome"
              value={produto.nome}
              onChange={handleProdutoChange}
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="fornecedorId">ID do Fornecedor:</label>
            <input
              type="text"
              id="fornecedorId"
              name="fornecedorId"
              value={produto.fornecedorId}
              onChange={handleProdutoChange}
              required
            />
          </div>
          <button type="submit">Cadastrar Produto</button>
        </form>
      </div>
    </div>
  );
}

export default Cadastro;
