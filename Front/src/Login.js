// Login.js
import React, { useState } from 'react';
import { Form, Button, Col, Container, Row } from 'react-bootstrap';
import { BsMoon, BsSun } from 'react-icons/bs';
import { Link } from 'react-router-dom'; // Importe Link para criar o link de navegação

function Login() {

  return (
    <div className= {`container mt-5`}>
      <Container>
        <Row className="justify-content-center">
          <Col xs={12} md={6}>
            <h2 className="mb-4">Login</h2>
            <Form className="mb-3">
              <Form.Group controlId="formBasicEmail">
                <Form.Label>Email</Form.Label>
                <Form.Control type="email" placeholder="Digite o e-mail cadastrado" />
              </Form.Group>

              <Form.Group controlId="formBasicPassword">
                <Form.Label>Senha</Form.Label>
                <Form.Control type="password" placeholder="Digite sua senha" />
              </Form.Group>
              </Form>
              <Button variant="primary" type="submit" className="mb-3">
                Submit
              </Button>
          

            <p className="mt-3">Não tem login? <Link to="/cadastro">Faça seu cadastro</Link>.</p>
          </Col>
        </Row>
      </Container>
    </div>
  );
}

export default Login;
