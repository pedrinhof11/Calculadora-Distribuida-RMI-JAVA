# Calculadora-Distribuida-RMI-JAVA

## O Projeto consiste em uma 4 aplicações:

### Aplicação Cliente 
- Envia solicitações de calculos ao Servidor Master, através de um protocolo simple de troca de mensagens via Socket. 
- E recebe os calculos resolvidos. 
  
### Servidor Master
- Recebe as solitações do calculo da Aplicação Cliente, via socket e encaminhará as mesmas, via RMI para servidores slave.
- Realiza o escalonamento e tomada de decição de quais servidores slave que irão resolver o calculo;
- Recebe a resposta servidor slave e reenvia os calculos com a resposta para a aplicação cliente;
    
### Servidor Slave Básico
 - Resgistra-se no servidor Master
 - E encarregado de realizar as operações básicas (soma, subtração, divisão e multiplicação)
 
### Servidor Slave Especiais
 - Resgistra-se no servidor Master
 - E encarregado de realizar as operações Especiais (Porcentagem, raiz quadrada e potenciação) 

