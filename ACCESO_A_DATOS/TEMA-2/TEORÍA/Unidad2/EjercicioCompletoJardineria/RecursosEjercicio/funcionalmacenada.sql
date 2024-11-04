CREATE OR REPLACE
  FUNCTION veroficina( cod VARCHAR2,   ciudad OUT VARCHAR2 ,
      pais OUT VARCHAR2, region OUT VARCHAR2 , direcc OUT VARCHAR2)
    RETURN NUMBER
  AS
    cuenta NUMBER:=-1;
  BEGIN
    SELECT COUNT(*) INTO cuenta FROM empleados WHERE codigooficina = cod;
    IF (cuenta=0) THEN
      ciudad :='NO EXISTE OFICINA';
      pais   :='NO EXISTE OFICINA';
    ELSE
      SELECT ciudad,  pais,  region,  lineadireccion1
      INTO ciudad,  pais,    region,   direcc
      FROM oficinas  WHERE codigooficina = cod;
    END IF;
    RETURN CUENTA;
  END;
/
