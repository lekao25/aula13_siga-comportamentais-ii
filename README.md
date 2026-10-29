# SIGA — Atividade de padrões comportamentais II: Template Method e State (código inicial)

**Técnicas de Programação II (TP2) · Aula 13** — CST em Desenvolvimento de Software Multiplataforma · Fatec de Porto Ferreira

Este é o **código inicial** da atividade prática da Aula 13, a última com padrões de projeto. Ele compila e executa — e a saída já mostra um aluno irregular deixando rastro no log de um documento que nunca foi emitido. São **três deslizes**, e os dois padrões da aula resolvem todos eles.

## Estrutura do projeto

```
siga-comportamentais-ii/
└── src/
    └── siga/
        ├── Aluno.java                (entidade de domínio; pronta)
        ├── LogAcademico.java         (registro de emissões; pronto)
        ├── HistoricoEscolar.java     (rito duplicado — deslize 1)
        ├── DeclaracaoVinculo.java    (rito duplicado E fora de ordem — deslizes 1 e 2)
        ├── AtestadoFrequencia.java   (rito duplicado — deslize 1)
        ├── Matricula.java            (ciclo de vida em condicionais — deslize 3)
        └── Main.java                 (demonstra os três deslizes)
```

## Como compilar e executar

Pré-requisito: JDK 17 ou superior (`java -version` para verificar).

```bash
# 1. Compilar (a saída vai para a pasta "bin")
javac -encoding UTF-8 -d bin src/siga/*.java

# 2. Executar
java -cp bin siga.Main
```

Execute antes de ler o restante deste arquivo: a saída **mostra os três deslizes acontecendo**.

## Os três deslizes propositais

| # | Deslize | Onde | Por que é um problema |
|---|---|---|---|
| 1 | **O rito de emissão duplicado** | As três classes de documento | Cinco passos — validar, cabeçalho, corpo, rodapé, registrar — e **quatro deles são idênticos** nas três classes. Só o corpo varia. Um quarto documento seria uma quarta cópia. |
| 2 | **Os passos fora de ordem** | `DeclaracaoVinculo` | Alguém inverteu: o **registro no log acontece antes da validação**. Execute com um aluno irregular e veja — o documento não é emitido, mas já consta no log. A ordem dos passos é uma regra, e regra copiada diverge. |
| 3 | **O ciclo de vida em condicionais sobre uma String** | `Matricula` | As regras de transição estão espalhadas por três métodos, e não se lê o ciclo de vida inteiro em lugar nenhum. O estado "concluída" exigiria alterar os três. E, sendo a situação um texto, um erro de digitação só apareceria em produção. |

## Sua tarefa

Siga as etapas da ficha de atividade prática:

1. **Localizar a inversão.** Identifique, nas três classes de documento, o rito duplicado, e aponte neste README o ponto exato em que a `DeclaracaoVinculo` está fora de ordem e qual é a consequência observável.
2. **Criar o esqueleto.** Escreva a classe abstrata `DocumentoAcademico` com o método-template `emitir(Aluno)` declarado **`final`**, os passos invariantes (`validar`, `cabecalho`, `rodape`, `registrarEmissao`) implementados nela, e `corpo(Aluno)` como passo abstrato.
3. **Converter os documentos.** Faça as três classes estenderem `DocumentoAcademico`, implementando apenas o `corpo`. Use um **gancho** `exigeAssinatura()`, com padrão `true`, que o `AtestadoFrequencia` sobrescreve para `false`.
4. **Criar o State.** Escreva a interface `EstadoMatricula` e as quatro classes `PreMatriculada`, `Confirmada`, `Trancada` e `Cancelada`, com as transições **dentro** delas. Os três métodos públicos de `Matricula` devem ficar com uma linha cada, sem nenhum `if`.
5. **Medir o ganho.** Acrescente o estado `Concluida`, alcançável a partir de `Confirmada`, e registre neste README **quantos arquivos você precisou tocar** — comparando com os três métodos que seriam alterados na versão inicial.

## Critério de sucesso

Ao final, verifique que: (a) o texto do cabeçalho e o do rodapé existem em **um único lugar**; (b) o método-template é `final`, de modo que nenhuma subclasse consiga inverter a ordem; (c) emitir qualquer documento para um aluno irregular **não deixa rastro no log**; (d) a palavra `if` não aparece em nenhum dos três métodos públicos de `Matricula`; e (e) acrescentar o estado `Concluida` custou **dois arquivos** — a classe nova e a `Confirmada`, que passa a oferecer a transição.

> O item (c) é o teste que prova o Template Method. Se a `DeclaracaoVinculo` ainda registrar antes de validar depois da refatoração, é porque o método-template não ficou `final` — ou porque a subclasse o sobrescreveu em vez de preencher o `corpo`.

## A linha que você deve conseguir apontar

No final da etapa 4, uma das suas classes de estado conterá algo como:

```java
matricula.mudarPara(new Confirmada());
```

Essa é a linha que distingue o **State** do **Strategy** da aula passada. Uma política de desconto calcula e devolve um valor; ela jamais diria `matricula.setPolitica(new DescontoServidor())`. No State, os estados se conhecem e executam as transições. Se você conseguir explicar isso no README, entendeu o padrão.

## Padrão de entrega

Conforme a ficha de atividade prática: identificadores em português, um arquivo `.java` por classe pública, código formatado, entrega no repositório Git com README e commits descritivos. O uso de IA para gerar o código é proibido nesta atividade (ver seção 5.3 da ficha).

> **Projeto Integrador — Etapa 2:** a entrega é **hoje**. Ela exige ao menos um padrão estrutural (Aulas 10 e 11) e ao menos um comportamental (Aulas 12 e 13), cada um com justificativa que descreva **o problema do SIGA antes de nomear o padrão**. O critério que mais pesa é a pertinência: dois padrões bem justificados valem mais que seis decorativos.

## Respostas da Atividade

**1 - Localizar a inversão:**
A inversão ocorre na classe `DeclaracaoVinculo`, onde a instrução `LogAcademico.registrar("DECLARACAO", aluno);` é executada antes da validação `if (!aluno.isRegular())`. A consequência observável é que, ao tentar emitir o documento para um aluno irregular, o sistema lança uma exceção impedindo a emissão, mas o aluno já deixou um rastro indevido gravado no log.