
CREATE TABLE "users" (
      "id" bigint generated by default as identity,
      "cpf" varchar(11),
      "data_nascimento" date,
      "email" varchar(255),
      "nome" varchar(255),
      "sobre_nome" varchar(255),
      primary key ("id")
);
