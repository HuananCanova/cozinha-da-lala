import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import { Card } from './components/card/card';
import { useCardapioData } from './hooks/useCardapioData';
import { Navbar } from './components/navbar/navbar';
import { Login } from './pages/login/login';
import Register from './pages/register/register';
import Perfil from './pages/perfil/perfil';
import Cardapio from './pages/cardapio/cardapio';
import EditarCardapio from './pages/cardapio/cardapio'; // Corrigido o caminho

function App() {
  const { data, isLoading, isError } = useCardapioData();
  const [error, setError] = useState<string>('');
  const role = localStorage.getItem('role');

  const handleDelete = async (id: number) => {
    // Implemente a função handleDelete
  };

  if (isLoading) {
    return <div>Loading...</div>;
  }

  if (isError) {
    return <div>Failed to fetch data</div>;
  }

  return (
    <Router>
      <Navbar />
      <div className="container">
        <Routes>
          <Route
            path="/"
            element={
              <>
                <h1>Cardápio da Semana</h1>
                <div className="card-grid">
                  {data?.map((cardapioData) => (
                    <Card
                      key={cardapioData.id}
                      id={cardapioData.id}
                      diaSemana={cardapioData.diaSemana}
                      descricao={cardapioData.descricao}
                      preco={cardapioData.preco}
                      isAdmin={role === 'ADMIN'}
                      onDelete={() => handleDelete(cardapioData.id)}
                    />
                  ))}
                </div>
              </>
            }
          />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/perfil" element={<Perfil />} />
          <Route path="/cardapio" element={<Cardapio />} />
          <Route path="/edit-menu/:id" element={<EditarCardapio />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
