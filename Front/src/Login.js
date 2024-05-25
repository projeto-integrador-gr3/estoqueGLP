// Login.js
import React, { useState } from 'react';
import { Form, Button, Col, Container, Row, Alert } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom'; 
import axios from 'axios'; 

function Login({ setIsAuthenticated }) {
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();
    setError('');
    setSuccess('');

    try {
      const response = await axios.post('http://localhost:8080/login', {
        login: email,
        senha: senha
      });

      const { token, isAdmin } = response.data;
      localStorage.setItem('token', token);
      localStorage.setItem('isAdmin', isAdmin);
      setIsAuthenticated(true);
      setSuccess('Usuário logado com sucesso!');
      setTimeout(() => {
        navigate('/transacoes');
      }, 1000);
    } catch (error) {
      setError('Erro ao fazer login. Verifique seu login ou senha.');
    }
  };

  return (
    <div className="container mt-5">
      <Container>
        <Row className="justify-content-center">
          <Col xs={12} md={6}>
            <h2 className="mb-4">Login</h2>
            {error && <Alert variant="danger">{error}</Alert>}
            {success && <Alert variant="success">{success}</Alert>}
            <Form onSubmit={handleSubmit} className="mb-3">
              <Form.Group controlId="formBasicEmail">
                <Form.Label>Email</Form.Label>
                <Form.Control
                  type="email"
                  placeholder="Digite o e-mail cadastrado"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                />
              </Form.Group>
              <Form.Group controlId="formBasicPassword">
                <Form.Label>Senha</Form.Label>
                <Form.Control
                  type="password"
                  placeholder="Digite sua senha"
                  value={senha}
                  onChange={(e) => setSenha(e.target.value)}
                />
              </Form.Group>
              <Button variant="primary" type="submit" className="mb-3">
                Submit
              </Button>
            </Form>
            <p className="mt-3">
              Não tem login? <Link to="/cadastro">Faça seu cadastro</Link>.
            </p>
          </Col>
        </Row>
      </Container>
    </div>
  );
}

export default Login;
