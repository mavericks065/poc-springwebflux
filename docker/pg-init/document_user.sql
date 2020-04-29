CREATE TABLE document_user (
	document_id uuid NOT NULL,
	user_entity_id int8 NOT NULL,
	CONSTRAINT document_user_document_id_key UNIQUE (document_id)
);

INSERT INTO document_user (document_id, user_entity_id) VALUES ('7bebc4cb-1834-427b-b806-6e27710fde05', '1');
INSERT INTO document_user (document_id, user_entity_id) VALUES ('a79aa9fd-ce97-4e4a-b0c0-a3aed617d039', '1');
