CREATE TABLE users (
  id  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  document_number VARCHAR(80) UNIQUE ,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  email VARCHAR(150) NOT NULL UNIQUE ,
  birth_date DATE NOT NULL,
  created_at TIMESTAMP DEFAULT NOW(),

  CONSTRAINT chk_users_email
      CHECK (
          email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'
  )
);

CREATE TABLE authors (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    isni VARCHAR(16) NOT NULL UNIQUE,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,

    CONSTRAINT chk_author_isni
        CHECK (isni ~ '^[0-9X]{16}$')
    );
CREATE TABLE books (
    id  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(200) NOT NULL,
    isbn VARCHAR(20) NOT NULL UNIQUE ,
    edition VARCHAR(20) NOT NULL,
    date_published DATE,
    author_id UUID NOT NULL,

    CONSTRAINT FK_BOOKS_AUTHOR FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE
);



CREATE TABLE book_replica (
    id  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    code_inventory VARCHAR(50) NOT NULL UNIQUE ,
    status VARCHAR(50) NOT NULL DEFAULT  'AVAILABLE',
    created_at TIMESTAMP DEFAULT NOW(),
    book_id UUID NOT NULL,

    CONSTRAINT FK_BOOK_REPLICA_BOOK
        FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE ,

    CONSTRAINT chk_code_inventory
        CHECK (
            code_inventory ~ '^LIB-[0-9]{4}$'
    ),
    CHECK (status IN ('AVAILABLE','DAMAGED','LOST', 'LOANED'))
);



CREATE TABLE loans (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    loan_date   DATE NOT NULL,
    due_date    DATE NOT NULL,
    date_return DATE,
    status VARCHAR(20) NOT NULL DEFAULT  'AVAILABLE',
    user_id UUID NOT NULL,
    book_copy_id UUID NOT NULL,

    CONSTRAINT FK_LOANS_USER
        FOREIGN KEY (user_id)
            REFERENCES users(id),

    CONSTRAINT FK_LOANS_BOOK
        FOREIGN KEY (book_copy_id)
            REFERENCES book_replica(id) ,

    CONSTRAINT chk_loan_dates
        CHECK (due_date >= loan_date),

    CHECK (status IN ('ACTIVE','OVERDUE','RETURNED'))

);







