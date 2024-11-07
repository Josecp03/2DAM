package chuletario;

public class CrearTabla {

	String sql = "		CREATE TABLE ClientesSinPedidos (" + "				  CodigoCliente number(6) NOT NULL,"
			+ "				  NombreCliente varchar2(50) NOT NULL,"
			+ "				  NombreContacto varchar2(30) DEFAULT NULL,"
			+ "				  ApellidoContacto varchar2(30) DEFAULT NULL,"
			+ "				  Telefono varchar2(15) NOT NULL," + "				  Fax varchar2(15) NOT NULL,"
			+ "				  LineaDireccion1 varchar2(50) NOT NULL,"
			+ "				  LineaDireccion2 varchar2(50) DEFAULT NULL,"
			+ "				  Ciudad varchar2(50) NOT NULL," + "				  Region varchar2(50) DEFAULT NULL,"
			+ "				  Pais varchar2(50) DEFAULT NULL,"
			+ "				  CodigoPostal varchar2(10) DEFAULT NULL,"
			+ "				  CodigoEmpleadoRepVentas number(6) DEFAULT NULL,"
			+ "				  LimiteCredito number(15,2) DEFAULT NULL,"
			+ "				  PRIMARY KEY (CodigoCliente),"
			+ "				  CONSTRAINT ClientesSinPedidos_EmpleadosFK FOREIGN KEY (CodigoEmpleadoRepVentas) REFERENCES Empleados (CodigoEmpleado)"
			+ "				)";

}
