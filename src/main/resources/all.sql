CREATE TABLE IF NOT EXISTS owners (
                                      id SERIAL PRIMARY KEY,
                                      name VARCHAR(100) NOT NULL,
                                      email VARCHAR(100) NOT NULL UNIQUE,
                                      phone_number VARCHAR(20)
);

-- Create cars table
CREATE TABLE IF NOT EXISTS cars (
                                    id SERIAL PRIMARY KEY,
                                    make VARCHAR(50) NOT NULL,
                                    model VARCHAR(50) NOT NULL,
                                    production_year INTEGER,
                                    registration_number VARCHAR(20) UNIQUE,
                                    owner_id INTEGER REFERENCES owners(id)
);