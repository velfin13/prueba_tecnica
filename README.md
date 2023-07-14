```
CREATE OR REPLACE PROCEDURE reset_intentos_sesion()
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE users SET intentos_sesion = 0 WHERE intentos_sesion > 0;
    COMMIT;
END;
$$;
```