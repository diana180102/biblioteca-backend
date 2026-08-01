-- ============================
-- AUTHORS
-- ============================

INSERT INTO authors (
    isni,
    first_name,
    last_name
) VALUES
      (
          '000000012146438X',
          'Gabriel',
          'García Márquez'
      ),
      (
          '000000012281955X',
          'J.K.',
          'Rowling'
      ),
      (
          '0000000121032683',
          'George',
          'Orwell'
      ),
      (
          '0000000121464388',
          'J.R.R.',
          'Tolkien'
      ),
      (
          '0000000121478925',
          'Isaac',
          'Asimov'
      );

-- ============================
-- BOOKS
-- ============================

INSERT INTO books (
    title,
    isbn,
    edition,
    date_published,
    author_id
)
VALUES
    (
        'Cien años de soledad',
        '9780307474728',
        '1st',
        '1967-05-30',
        (SELECT id FROM authors WHERE isni = '000000012146438X')
    ),
    (
        'Harry Potter y la piedra filosofal',
        '9788478884452',
        '1st',
        '1997-06-26',
        (SELECT id FROM authors WHERE isni = '000000012281955X')
    ),
    (
        '1984',
        '9780451524935',
        '1st',
        '1949-06-08',
        (SELECT id FROM authors WHERE isni = '0000000121032683')
    ),
    (
        'El Señor de los Anillos',
        '9780261102385',
        '2nd',
        '1954-07-29',
        (SELECT id FROM authors WHERE isni = '0000000121464388')
    ),
    (
        'Fundación',
        '9780553293357',
        '1st',
        '1951-06-01',
        (SELECT id FROM authors WHERE isni = '0000000121478925')
    );

