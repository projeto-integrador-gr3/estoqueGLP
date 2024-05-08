import logo from "../src/Image/gas.jpg";
import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "./css/App.css";

function Home() {
  const [entrada, setEntrada] = useState("");
  const [saida, setSaida] = useState("");
  const [tipoEstoque, setTipoEstoque] = useState("P13");
  const [estoqueFinal, setEstoqueFinal] = useState(0);

  const handleTipoEstoqueChange = (e) => {
    setTipoEstoque(e.target.value);
  };

  const handleEntradaChange = (e) => {
    setEntrada(e.target.value);
  };

  const handleSaidaChange = (e) => {
    setSaida(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Tipo de Estoque:", tipoEstoque);
    console.log("Entrada:", entrada);
    console.log("Saída:", saida);
    setEstoqueFinal(0);
  };

  return (
    <div className="container-fluid">
      <header className="App-login-header bg-dark text-light p-3 rounded">
        <div className="btn-group" role="group" aria-label="Opções de Login">
          <a className="btn btn-outline-light" href="/login">
            Login
          </a>
          <a
            className="btn btn-outline-light"
            href="./cadastro" 
          >
            Cadastro
          </a>
        </div>
      </header>
      <header className="App-header bg-dark text-light p-5 rounded">
        <img src={logo} className="App-logo" alt="logo" />
        <h1 className="mb-4">Controle de Estoque de GLP</h1>
        <div className="mt-3">
          <p className="mb-1">Estoque Final:</p>
          <div className="estoque-final">{estoqueFinal}</div>
        </div>
        <div className="mt-3"></div>
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label htmlFor="tipoEstoque" className="form-label">
              Tipo de Estoque:
            </label>
            <select
              id="tipoEstoque"
              value={tipoEstoque}
              onChange={handleTipoEstoqueChange}
              className="form-select"
            >
              <option value="">Selecione o tipo de estoque</option>
              <option value="P13">P13</option>
              <option value="P20">P20</option>
              <option value="P45">P45</option>
            </select>
          </div>
          <div className="d-flex mb-3">
            <div className="me-3">
              <label htmlFor="entrada" className="form-label">
                Entrada de Estoque:
              </label>
              <input
                type="number"
                id="entrada"
                value={entrada}
                onChange={handleEntradaChange}
                className="form-control"
              />
            </div>
            <div>
              <label htmlFor="saida" className="form-label">
                Saída de Estoque:
              </label>
              <input
                type="number"
                id="saida"
                value={saida}
                onChange={handleSaidaChange}
                className="form-control"
              />
            </div>
          </div>
          <button type="submit" className="btn btn-primary">
            Atualizar Estoque
          </button>
        </form>
      </header>
    </div>
  );
}

export default Home;
