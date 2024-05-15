# ToolsChallenge
Api de Pagamentos feita para resolver o teste prático da empresa Tools Software. 
O sistema permite realizar operações referentes a pagamentos como processar e consultar pagamentos e estornos


## Pré-requisitos
- **Java Development Kit (JDK)**: Versão 17.
- **Gerenciador de Dependências**: Maven.

## Executando aplicação

Para executar a API em seu ambiente local, siga as instruções abaixo:

1. **Clone o Repositório**:  
    - git clone https://github.com/guilhermeozana/ToolsChallenge.git

2. **Navegue até o Diretório do Projeto**: 
    - cd ToolsChallenge

3. **Build do Projeto**:
    - mvn clean install

4. **Execute a Aplicação**: 
    - java -jar target/toolschallenge-1.0.jar  

5. **Acesso à API**: 
    - A API estará disponível em http://localhost:8080. Todos os endpoints podem ser visualizados em http://localhost:8080/swagger-ui/index.html.

Certifique-se de ter o JDK 17 e o Maven instalados em sua máquina conforme mencionado nos pré-requisitos.

## Estrutura do Projeto

O projeto segue uma arquitetura baseada em camadas, com separação clara entre a camada de controle, serviço e acesso a dados visando maior organização do projeto.
Também foram utilizados alguns padrões de projeto para facilitar a manutenção e extensão da aplicação.



