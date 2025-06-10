# 🚌 Sistema de Controle e Vendas de Passagens (SCVP)

Aplicação Java desenvolvida para gerenciamento de vendas de passagens, cadastro de passageiros, funcionários e modais de transporte. Criado como projeto acadêmico para a empresa fictícia **Vai&Volta Viagens (VVV)**.

---

## 📌 Sobre o Projeto

O **SCVP** tem como objetivo facilitar o controle operacional da empresa Vai&Volta Viagens, centralizando as informações de passageiros, modais e reservas em uma interface visual intuitiva. O sistema utiliza **Java** com **Swing** para interface gráfica, persistência via **JDBC**, e estrutura em MVC com DAOs para separação de responsabilidades.

---

## 🚀 Tecnologias Utilizadas

- Java 17
- Swing (Interface Gráfica)
- JDBC (Conexão com banco de dados)
- PostgreSQL
- IDE IntelliJ / NetBeans
- Maven

---

## 📦 Como Executar

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/vvv.git
cd vvv
````

### 2. Configure o banco de dados

* Crie um banco no MySQL.
* Execute o script `src/main/java/database/bd.sql`.

### 3. Compile o projeto

Caso use Maven:

```bash
mvn clean install
```

Ou, abra com sua IDE Java favorita e execute `TelaPrincipal.java`.

---

## 🛠️ Estrutura do Projeto

```
vvv/
├── pom.xml
├── src/
│   └── main/
│       └── java/
│           ├── database/
│           │   └── bd.sql
│           ├── img/
│           │   └── logo.png
│           └── vvv/
│               ├── controller/
│               │   ├── ModalController.java
│               │   └── PassageiroController.java
│               ├── model/
│               │   ├── Conexao.java
│               │   ├── Funcionario.java
│               │   ├── ModalTransporte.java
│               │   ├── Passageiro.java
│               │   ├── PontoVenda.java
│               │   ├── Reserva.java
│               │   └── DAOs/
│               │       ├── ModalDAO.java
│               │       └── PassageiroDAO.java
│               └── view/
│                   ├── TelaPrincipal.java
│                   └── Passageiro/
│                       ├── TelaConsultarPassageiro.java
│                       ├── TelaCriarPassageiro.java
│                       └── TelaEditarPassageiro.java
```

---

## 💡 Funcionalidades

* ✅ Cadastro, edição e consulta de passageiros
* ✅ Gerenciamento de modais de transporte
* ✅ Interface gráfica com navegação por abas e formulários
* ✅ Banco de dados relacional com script de criação
* ✅ Separação entre camadas: modelo, visual e controle

---

## 🤝 Contribuição

Sinta-se à vontade para sugerir melhorias ou abrir issues!

1. Faça um fork
2. Crie uma branch: `git checkout -b feature/sua-feature`
3. Commit: `git commit -m 'feat: adiciona nova funcionalidade'`
4. Push: `git push origin feature/sua-feature`
5. Abra um Pull Request

---

## 📄 Licença

Este projeto está licenciado sob a Licença MIT — consulte o arquivo [LICENSE](LICENSE) para mais informações.

---

## 📬 Contato

Projeto acadêmico — desenvolvido por Lucas Bonfim

[lucascardoso2103@gmail.com](mailto:lucascardoso2103@gmail.com) | [LinkedIn](https://www.linkedin.com/in/lucaasbonfim/)


