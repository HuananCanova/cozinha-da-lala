import React from 'react';
import './card.css';

interface CardProps {
    diaSemana: string;
    preco: number;
    descricao: string;
}

export function Card({ diaSemana, preco, descricao }: CardProps) {
    return (
        <div className="card">
            <h1>{diaSemana}</h1>
            <p>{descricao}</p>
            <h3>Preço: R${preco.toFixed(2)}</h3>
            <button>Encomendar</button>
        </div>
    );
}
