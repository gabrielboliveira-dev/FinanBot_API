CREATE TABLE goals (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    target_amount NUMERIC(19, 2) NOT NULL,
    current_amount NUMERIC(19, 2) NOT NULL DEFAULT 0.0,
    deadline TIMESTAMP,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_goals_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE budgets (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    category_id UUID NOT NULL,
    amount NUMERIC(19, 2) NOT NULL,
    period VARCHAR(50) NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    CONSTRAINT fk_budgets_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_budgets_category FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE alerts (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    type VARCHAR(50) NOT NULL,
    message VARCHAR(255) NOT NULL,
    trigger_date TIMESTAMP NOT NULL,
    is_read BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_alerts_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE recurring_expenses (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    category_id UUID NOT NULL,
    description VARCHAR(255) NOT NULL,
    amount NUMERIC(19, 2) NOT NULL,
    frequency VARCHAR(50) NOT NULL,
    next_due_date TIMESTAMP NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_recurring_expenses_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_recurring_expenses_category FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE expense_groups (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_by UUID NOT NULL,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_expense_groups_user FOREIGN KEY (created_by) REFERENCES users(id)
);

CREATE TABLE group_members (
    group_id UUID NOT NULL,
    user_id UUID NOT NULL,
    PRIMARY KEY (group_id, user_id),
    CONSTRAINT fk_group_members_group FOREIGN KEY (group_id) REFERENCES expense_groups(id),
    CONSTRAINT fk_group_members_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE shared_expenses (
    id UUID PRIMARY KEY,
    group_id UUID NOT NULL,
    paid_by UUID NOT NULL,
    description VARCHAR(255) NOT NULL,
    amount NUMERIC(19, 2) NOT NULL,
    date TIMESTAMP NOT NULL,
    CONSTRAINT fk_shared_expenses_group FOREIGN KEY (group_id) REFERENCES expense_groups(id),
    CONSTRAINT fk_shared_expenses_user FOREIGN KEY (paid_by) REFERENCES users(id)
);

CREATE TABLE investments (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    quantity NUMERIC(19, 6) NOT NULL,
    purchase_price NUMERIC(19, 2) NOT NULL,
    current_price NUMERIC(19, 2) NOT NULL,
    purchase_date TIMESTAMP NOT NULL,
    CONSTRAINT fk_investments_user FOREIGN KEY (user_id) REFERENCES users(id)
);