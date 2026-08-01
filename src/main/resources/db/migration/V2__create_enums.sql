CREATE TYPE copy_status AS ENUM (
    'AVAILABLE',
    'LOANED',
    'LOST',
    'DAMAGED'
);

CREATE TYPE loan_status AS ENUM (
    'ACTIVE',
    'OVERDUE',
    'RETURNED'
);