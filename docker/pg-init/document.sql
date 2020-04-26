create table document
(
    id                    uuid                        not null
        constraint document_pkey
            primary key,
    document_category     text,
    original_file_name    text,
    s3_path               text,
    document_status       text
);

INSERT INTO document (id, document_category, original_file_name, s3_path, document_status) VALUES ('7bebc4cb-1834-427b-b806-6e27710fde05', 'PASSPORT', 'Passport_nicolas.png', 'documents/users/1/7bebc4cb-1834-427b-b806-6e27710fde05.png', 'UNCHECKED');
INSERT INTO document (id, document_category, original_file_name, s3_path, document_status) VALUES ('a79aa9fd-ce97-4e4a-b0c0-a3aed617d039', 'ID', 'id.png', 'documents/users/1/a79aa9fd-ce97-4e4a-b0c0-a3aed617d039.png', 'UNCHECKED');
