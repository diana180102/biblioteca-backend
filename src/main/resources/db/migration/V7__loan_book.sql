-- ============================
-- LOANS
-- ============================

INSERT INTO loans (
    loan_date,
    due_date,
    date_return,
    status,
    user_id,
    book_copy_id
)
VALUES

-- Préstamo ACTIVO
(
    '2026-07-28',
    '2026-08-11',
    NULL,
    'ACTIVE',
    (SELECT id FROM users WHERE document_number = '1001234567'),
    (SELECT id FROM book_replica WHERE code_inventory = 'LIB-0002')
),

-- Préstamo ACTIVO
(
    '2026-07-29',
    '2026-08-12',
    NULL,
    'ACTIVE',
    (SELECT id FROM users WHERE document_number = '1002345678'),
    (SELECT id FROM book_replica WHERE code_inventory = 'LIB-0006')
),

-- Préstamo ACTIVO
(
    '2026-07-30',
    '2026-08-13',
    NULL,
    'ACTIVE',
    (SELECT id FROM users WHERE document_number = '1003456789'),
    (SELECT id FROM book_replica WHERE code_inventory = 'LIB-0011')
),

-- Préstamo devuelto
(
    '2026-06-01',
    '2026-06-15',
    '2026-06-12',
    'RETURNED',
    (SELECT id FROM users WHERE document_number = '1004567890'),
    (SELECT id FROM book_replica WHERE code_inventory = 'LIB-0001')
),

-- Préstamo devuelto con retraso
(
    '2026-05-10',
    '2026-05-24',
    '2026-05-28',
    'RETURNED',
    (SELECT id FROM users WHERE document_number = '1005678901'),
    (SELECT id FROM book_replica WHERE code_inventory = 'LIB-0003')
),

-- Préstamo devuelto
(
    '2026-04-15',
    '2026-04-29',
    '2026-04-27',
    'RETURNED',
    (SELECT id FROM users WHERE document_number = '1006789012'),
    (SELECT id FROM book_replica WHERE code_inventory = 'LIB-0007')
);