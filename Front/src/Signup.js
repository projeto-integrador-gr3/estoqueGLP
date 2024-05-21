// Signup.js
import React, { useState } from 'react';
import { Form, Button, Col, Container, Row, Alert } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

function Signup() {

  const [nome, setNome] = useState('');
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
      const response = await axios.post('http://localhosroott:8080/usuarios', {
        nome: nome,
        login: email,
        senha: senha
      });

      setSuccess('Usuário cadastrado com sucesso!');
      setTimeout(() => {
        navigate('/');
      }, 1000);
    } catch (error) {
      setError('Erro ao cadastrar. Por favor, tente novamente.');
    }
  };
  
  return (
    <div className={`container mt-5`}>
      <Container>
        <Row className="justify-content-center">
          <Col xs={12} md={6}>
            <h2 className="mb-4">Cadastro</h2>
            {error && <Alert variant="danger">{error}</Alert>}
            {success && <Alert variant="success">{success}</Alert>}
            <Form onSubmit={handleSubmit} className="mb-4">
              <Form.Group controlId="formBasicName">
                <Form.Label>Nome</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="Digite o nome da sua revenda"
                  value={nome}
                  onChange={(e) => setNome(e.target.value)}
                />
              </Form.Group>

              <Form.Group controlId="formBasicEmail">
                <Form.Label>Email</Form.Label>
                <Form.Control
                  type="email"
                  placeholder="Digite seu email"
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
                Cadastrar
              </Button>
            </Form>
            <p className="mt-3">
              Já tem uma conta? <Link to="/">Faça login</Link>.
            </p>
          </Col>
        </Row>
      </Container>
    </div>
  );
}

export default Signup;
