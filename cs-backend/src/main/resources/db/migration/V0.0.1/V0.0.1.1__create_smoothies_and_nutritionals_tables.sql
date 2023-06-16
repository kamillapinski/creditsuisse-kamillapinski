CREATE SEQUENCE seq_smoothies_id MINVALUE 1 INCREMENT BY 1;

CREATE TABLE smoothies
(
    id                 BIGINT PRIMARY KEY     DEFAULT nextval('seq_smoothies_id'),
    added_at           TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    name               VARCHAR(255)  NOT NULL,
    description        VARCHAR(1024) NOT NULL,
    available_in_stock INT           NOT NULL DEFAULT 0
);

CREATE SEQUENCE seq_nutritional_values_id MINVALUE 1 INCREMENT BY 1;

CREATE TABLE nutritional_values
(
    id          BIGINT PRIMARY KEY DEFAULT nextval('seq_nutritional_values_id'),
    added_at    TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    name        VARCHAR(255)                     NOT NULL,
    unit        VARCHAR(32)                      NOT NULL,
    value       DECIMAL                          NOT NULL,
    smoothie_id BIGINT REFERENCES smoothies (id) NOT NULL
)