-- ============================
-- BOOK COPIES
-- ============================

INSERT INTO book_replica (
    code_inventory,
    status,
    book_id
)
VALUES
-- Cien años de soledad
(
    'LIB-0001',
    'AVAILABLE',
    (SELECT id FROM books WHERE isbn = '9780307474728')
),
(
    'LIB-0002',
    'LOANED',
    (SELECT id FROM books WHERE isbn = '9780307474728')
),

-- Harry Potter
(
    'LIB-0003',
    'AVAILABLE',
    (SELECT id FROM books WHERE isbn = '9788478884452')
),
(
    'LIB-0004',
    'AVAILABLE',
    (SELECT id FROM books WHERE isbn = '9788478884452')
),
(
    'LIB-0005',
    'DAMAGED',
    (SELECT id FROM books WHERE isbn = '9788478884452')
),

-- 1984
(
    'LIB-0006',
    'LOANED',
    (SELECT id FROM books WHERE isbn = '9780451524935')
),
(
    'LIB-0007',
    'AVAILABLE',
    (SELECT id FROM books WHERE isbn = '9780451524935')
),

-- El Señor de los Anillos
(
    'LIB-0008',
    'AVAILABLE',
    (SELECT id FROM books WHERE isbn = '9780261102385')
),
(
    'LIB-0009',
    'LOST',
    (SELECT id FROM books WHERE isbn = '9780261102385')
),

-- Fundación
(
    'LIB-0010',
    'AVAILABLE',
    (SELECT id FROM books WHERE isbn = '9780553293357')
),
(
    'LIB-0011',
    'LOANED',
    (SELECT id FROM books WHERE isbn = '9780553293357')
),
(
    'LIB-0012',
    'AVAILABLE',
    (SELECT id FROM books WHERE isbn = '9780553293357')
);