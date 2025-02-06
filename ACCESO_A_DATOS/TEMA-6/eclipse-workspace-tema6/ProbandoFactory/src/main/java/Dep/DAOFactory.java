package Dep;

public abstract class DAOFactory {
    // Bases de datos soportadas
    public static final int MYSQL = 1;  
    public static final int NEODATIS = 2;
    public static final int ORACLE = 3;  // Agregado para Oracle

    public abstract DepartamentoDAO getDepartamentoDAO();

    public static DAOFactory getDAOFactory(int bd) {  
        switch (bd) {
            case MYSQL:
                return new SqlDbDAOFactory();
            case NEODATIS:
                return new NeodatisDAOFactory();
            case ORACLE:
                return new OracleDAOFactory();  // Agregado para Oracle
            default:
                return null;
        }
    }
}
