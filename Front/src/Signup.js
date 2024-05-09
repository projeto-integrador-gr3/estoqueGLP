// Signup.js
import React, { useState } from 'react';
import { Form, Button, Col, Container, Row } from 'react-bootstrap';
import { BsMoon, BsSun } from 'react-icons/bs';
import { Link } from 'react-router-dom';

function Signup() {
  

  return (
    <div className={`container mt-5 `}>
      <Container>
        <Row className="justify-content-center">
          <Col xs={12} md={6}>
            <h2 className="mb-4">Cadastro</h2>
            <Form className="mb-4">
              <Form.Group controlId="formBasicName">
                <Form.Label>Nome</Form.Label>
                <Form.Control type="text" placeholder="Digite o nome da sua revenda" />
              </Form.Group>

              <Form.Group controlId="formBasicPhone">
                <Form.Label>Telefone</Form.Label>
                <Form.Control type="text" placeholder="Digite o telefone da sua revenda" />
              </Form.Group>

              <Form.Group controlId="formBasicEmail">
                <Form.Label>Email</Form.Label>
                <Form.Control type="email" placeholder="Digite seu email" />
              </Form.Group>

              <Form.Group controlId="formBasicPassword">
                <Form.Label>Senha</Form.Label>
                <Form.Control type="password" placeholder="Digite sua senha" />
              </Form.Group>

 
            </Form>
            <Button variant="primary" type="submit" className="mb-3">
                Cadastrar
              </Button>
            <p className="mt-3">Já tem uma conta? <Link to="/">Faça login</Link>.</p>
          </Col>
        </Row>
      </Container>
    </div>
  );
}

export default Signup;
