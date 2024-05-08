import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './css/Cadastro.css';

function Cadastro() {
  const [cliente, setCliente] = useState({
    nome: "",
    endereco: "",
    telefone: "",
    
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCliente({ ...cliente, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch('URL_DO_SEU_BACKEND', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(cliente),
      });

      if (response.ok) {
        console.log("Cliente cadastrado com sucesso!");
        // Limpar os campos após o envio
        setCliente({
          nome: "",
          endereco: "",
          telefone: "",
        });
      } else {
        console.error("Erro ao cadastrar cliente");
      }
    } catch (error) {
      console.error("Erro ao conectar com o servidor:", error);
    }
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
                  <label htmlFor="nome" className="form-label">Nome:</label>
                  <input
                    type="text"
                    id="nome"
                    name="nome"
                    value={cliente.nome}
                    onChange={handleChange}
                    className="form-control"
                    placeholder="Nome da Revenda"
                    required
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="endereco" className="form-label">Endereço:</label>
                  <input
                    type="text"
                    id="endereco"
                    name="endereco"
                    value={cliente.endereco}
                    onChange={handleChange}
                    className="form-control"
                    placeholder="Endereço"
                    required
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="telefone" className="form-label">Telefone:</label>
                  <input
                    type="text"
                    id="telefone"
                    name="telefone"
                    value={cliente.telefone}
                    onChange={handleChange}
                    className="form-control"
                    placeholder="Telefone"
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
