import React from 'react';
import { useNavigate } from 'react-router-dom';
import './card.css';

interface CardProps {
    id: number; // ID do cardápio
    diaSemana: string;
    preco: number;
    descricao: string;
    isAdmin: boolean; // Flag indicando se o usuário é um ADMIN
    onDelete: () => void; // Função para excluir o cardápio
}

export function Card({ id, diaSemana, preco, descricao, isAdmin, onDelete }: CardProps) {
    const navigate = useNavigate();
    const role = localStorage.getItem('role');

    const handleEditClick = () => {
        navigate(`/edit-menu/${id}`);
    };

    const handleEncomendarClick = () => {
        navigate(`/fazer-pedido/${id}`); // Redirecionar para a página de fazer pedido com o ID do cardápio
    };

    return (
        <div className="card">
            <h1>{diaSemana}</h1>
            <p>{descricao}</p>
            <h3>Preço: R${preco.toFixed(2)}</h3>
            {isAdmin && (
                <div>
                    <button onClick={handleEditClick}>Editar Cardápio</button>
                    <button onClick={onDelete}>Excluir Cardápio</button>
                </div>
            )}
            {role === 'CLIENTE' && (
                <button onClick={handleEncomendarClick}>Encomendar</button>
            )}
        </div>
    );
}
