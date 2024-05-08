import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './css/Cadastro.css'; 

function Cadastro() {
  const [revenda, setRevenda] = useState("");
  const [telefone, setTelefone] = useState("");
  const [login, setLogin] = useState("");
  const [senha, setSenha] = useState("");

  const handleRevendaChange = (e) => {
    setRevenda(e.target.value);
  };

  const handleTelefoneChange = (e) => {
    setTelefone(e.target.value);
  };

  const handleLoginChange = (e) => {
    setLogin(e.target.value);
  };

  const handleSenhaChange = (e) => {
    setSenha(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Aqui você pode enviar os dados para o backend
    console.log("Revenda:", revenda);
    console.log("Telefone:", telefone);
    console.log("Login:", login);
    console.log("Senha:", senha);

    // Limpar os campos após o envio
    setRevenda("");
    setTelefone("");
    setLogin("");
    setSenha("");
  };

  return (
    <div className="container-fluid bg-dark text-light py-5"> 
      <div className="row justify-content-center">
        <div className="col-md-4">
          <div className="card">
            <div className="card-header bg-dark text-light"> 
              <h3>Cadastro</h3>
            </div>
            <div className="card-body">
              <form onSubmit={handleSubmit}>
                <div className="mb-3">
                  <label htmlFor="revenda" className="form-label">Nome da Revenda:</label>
                  <input
                    type="text"
                    id="revenda"
                    value={revenda}
                    onChange={handleRevendaChange}
                    className="form-control"
                    placeholder="Nome da Revenda"
                    required
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="telefone" className="form-label">Telefone:</label>
                  <input
                    type="text"
                    id="telefone"
                    value={telefone}
                    onChange={handleTelefoneChange}
                    className="form-control"
                    placeholder="Telefone:"
                    required
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="login" className="form-label">Login:</label>
                  <input
                    type="text"
                    id="login"
                    value={login}
                    onChange={handleLoginChange}
                    className="form-control"
                    placeholder="Escolha um nome para Login"
                    required
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="senha" className="form-label">Senha:</label>
                  <input
                    type="password"
                    id="senha"
                    value={senha}
                    onChange={handleSenhaChange}
                    className="form-control"
                    placeholder="Escolha uma senha"
                    required
                  />
                </div>
                <button type="submit" className="btn btn-primary">Cadastrar</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Cadastro;
