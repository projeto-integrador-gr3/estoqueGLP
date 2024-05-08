import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './css/Login.css'; 

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleUsernameChange = (e) => {
    setUsername(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Aqui você pode adicionar a lógica para autenticação do usuário

    // Limpar os campos após o envio
    setUsername("");
    setPassword("");
  };

  return (
    <body>
    <div className="container-fluid bg-dark text-light py-5">
      <div className="row justify-content-center">
        <div className="col-md-4">
          <div className="card">
            <div className="card-header bg-dark text-light">
              <h3>Login</h3>
            </div>
            <div className="card-body">
              <form onSubmit={handleSubmit}>
                <div className="mb-3">
                  <label htmlFor="username" className="form-label">Usuário:</label>
                  <input
                    type="text"
                    id="username"
                    value={username}
                    onChange={handleUsernameChange}
                    className="form-control"
                    required
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="password" className="form-label">Senha:</label>
                  <input
                    type="password"
                    id="password"
                    value={password}
                    onChange={handlePasswordChange}
                    className="form-control"
                    required
                  />
                </div>
                <button type="submit" className="btn btn-primary">Entrar</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    </body>
  );
}

export default Login;
