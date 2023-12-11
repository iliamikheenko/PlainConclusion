create schema if not exists plain_conclusion;

create table if not exists plain_conclusion.atom (
    id                      bigserial primary key,
    patient_id              text,
    conclusion_uuid         text,
    drug_name               text,
    nosology_id             text,
    nosology_name           text,
    therapy_type_name       text,
    therapy_name            text,
    therapy_ord             text,
    therapy_message_payload text,
    drug_group_id           text,
    drug_group_ord          text,
    drug_id                 text,
    drug_ord                text,
    hash int
);

create table if not exists plain_conclusion.conclusion(
        id bigserial primary key,
        patient_id text,
        conclusion_uuid text
        );


