import { useState } from 'react'
import './App.css'
import { CardapioData } from './interface/CardapioData';
import { Card } from './components/card/card';

function App() {
  const data: CardapioData[] = [
    { id: 1, diaSemana: "Segunda-feira", descricao: "Feijoada", valor: 25.00 },
    { id: 2, diaSemana: "Terça-feira", descricao: "Lasanha", valor: 30.00 },
    { id: 3, diaSemana: "Quarta-feira", descricao: "Frango Assado", valor: 20.00 },
    { id: 4, diaSemana: "Quinta-feira", descricao: "Frango Assado", valor: 20.00 },
    { id: 5, diaSemana: "Sexta-feira", descricao: "Frango Assado", valor: 20.00 },

    // Adicione mais itens conforme necessário
  ];

  return (
    <div className='container'>
      <h1>Cardápio da Semana</h1>
      <div className='card-grid'>
        {data.map(cardapioData => 
          <Card 
            key={cardapioData.id}
            diaSemana={cardapioData.diaSemana}
            descricao={cardapioData.descricao}
            preco={cardapioData.valor}
          />
        )}
      </div>
    </div>
  )
}

export default App
