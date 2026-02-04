# Gestão Financeira Doméstica

## Descrição
Este projeto permite gerir as despesas de uma casa e calcular quanto cada pessoa deve pagar de acordo com o seu salário.  
Foi desenvolvido em **Java** com **Swing** para a interface gráfica e **SQLite** para armazenar os dados permanentemente.

O sistema permite:
- Adicionar, editar e eliminar pessoas.
- Adicionar, editar e eliminar despesas, categorizadas.
- Calcular automaticamente quanto cada pessoa deve pagar com base nas despesas e nos salários.
- Gerir e consultar despesas por mês e ano.

## Funcionalidades

### Pessoas
- Adicionar nova pessoa com nome e salário.
- Editar dados existentes.
- Eliminar pessoas.
- Listagem automática de todas as pessoas registadas.

### Despesas
- Adicionar nova despesa com valor, categoria, mês e ano.
- Editar ou eliminar despesas existentes.
- Listagem automática de todas as despesas registadas.

### Cálculos
- Selecionar mês e ano para calcular o valor que cada pessoa deve pagar.
- O cálculo é proporcional ao salário de cada pessoa.
- Validação automática para evitar cálculos de meses/anos sem despesas registadas.

## Tecnologias
- **Java** – linguagem principal
- **Swing** – interface gráfica
- **SQLite** – base de dados para armazenamento persistente
- **MVC** – separação de Model, View e Controller

## Estrutura do Projeto

- **model** – classes que representam os dados (`Pessoa`, `Despesa`)
- **dao** – classes que comunicam com a base de dados (`PessoaDAO`, `DespesaDAO`)
- **controller** – classes que contêm a lógica do programa (`PessoaController`, `DespesaController`, `CalculoController`)
- **ui** – classes para a interface gráfica (`PessoasPanel`, `DespesaPanel`, `JanelaPrincipal`)
- **db** – ficheiro SQLite com as tabelas do projeto

## Como Executar
1. Abrir o projeto no **IntelliJ** ou **NetBeans**.
2. Certificar que o ficheiro **SQLite** está no caminho correto (`db/`).
3. Executar a classe principal `Main`.
4. Navegar pelas abas ou botões para gerir pessoas, despesas e calcular divisões.

## Observações
- A aplicação é escalável: novas pessoas e despesas podem ser adicionadas sem necessidade de alterar a lógica.
- Todos os dados são persistentes na base de dados SQLite.