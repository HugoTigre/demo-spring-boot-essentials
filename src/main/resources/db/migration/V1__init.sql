-- Users
CREATE TABLE public.book
(
  id    serial PRIMARY KEY NOT NULL,
  title text               NOT NULL,
  year  int                NOT NULL
);
COMMENT ON TABLE public.book
  IS 'books table';
