/*
 * Created on 20 nov. 2017 ( Time 23:22:58 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package ma.jberrich.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ma.jberrich.bean.Emp;
import ma.jberrich.dao.EmpDAO;
import ma.jberrich.dao.impl.jdbc.commons.GenericDAO;

/**
 * Emp DAO implementation 
 * 
 * @author Telosys Tools
 *
 */
public class EmpDAOImpl extends GenericDAO<Emp> implements EmpDAO {

	private final static String SQL_SELECT = 
		"select EMPNO, ENAME, JOB, AGE, SAL, DEPTNO from emp where EMPNO = ?";

	private final static String SQL_INSERT = 
		"insert into emp ( ENAME, JOB, AGE, SAL, DEPTNO ) values ( ?, ?, ?, ?, ? )";

	private final static String SQL_UPDATE = 
		"update emp set ENAME = ?, JOB = ?, AGE = ?, SAL = ?, DEPTNO = ? where EMPNO = ?";

	private final static String SQL_DELETE = 
		"delete from emp where EMPNO = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from emp";

	private final static String SQL_COUNT = 
		"select count(*) from emp where EMPNO = ?";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public EmpDAOImpl() {
		super();
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param empno;
	 * @return the new instance
	 */
	private Emp newInstanceWithPrimaryKey( int empno ) {
		Emp emp = new Emp();
		emp.setEmpno( empno );
		return emp ;
	}
	
	/**
	 * findAll added by JBERRICH
	 * @return
	 */
	@Override
	public List<Emp> findAll() {
		List<Emp> employes = new ArrayList<>();
		Connection conn = null;
		try {
			conn = getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT EMPNO FROM EMP");
			while (rs.next()) {
				employes.add(find(rs.getInt(1)));
			}
			rs.close();
		}  catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection(conn);
		}
		return employes;
	}

	//----------------------------------------------------------------------
	/**
	 * Finds a bean by its primary key 
	 * @param empno;
	 * @return the bean found or null if not found 
	 */
	@Override
	public Emp find( int empno ) {
		Emp emp = newInstanceWithPrimaryKey( empno ) ;
		if ( super.doSelect(emp) ) {
			return emp ;
		}
		else {
			return null ; // Not found
		}
	}
	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param emp
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( Emp emp ) {
		return super.doSelect(emp) ;
	}
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param emp
	 */
	@Override
	public int insert(Emp emp) {
		Long key = super.doInsertAutoIncr(emp);
		return key.intValue();
	}

    //----------------------------------------------------------------------
	/**
	 * Updates the given bean in the database 
	 * @param emp
	 * @return
	 */
	@Override
	public int update(Emp emp) {
		return super.doUpdate(emp);
	}	

    //----------------------------------------------------------------------
	/**
	 * Deletes the record in the database using the given primary key value(s) 
	 * @param empno;
	 * @return
	 */
	@Override
	public int delete( int empno ) {
		Emp emp = newInstanceWithPrimaryKey( empno ) ;
		return super.doDelete(emp);
	}

    //----------------------------------------------------------------------
	/**
	 * Deletes the given bean in the database 
	 * @param emp
	 * @return
	 */
	@Override
	public int delete( Emp emp ) {
		return super.doDelete(emp);
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param empno;
	 * @return
	 */
	@Override
	public boolean exists( int empno ) {
		Emp emp = newInstanceWithPrimaryKey( empno ) ;
		return super.doExists(emp);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param emp
	 * @return
	 */
	@Override
	public boolean exists( Emp emp ) {
		return super.doExists(emp);
	}

    //----------------------------------------------------------------------
	/**
	 * Counts all the records present in the database
	 * @return
	 */
	@Override
	public long count() {
		return super.doCountAll();
	}

    //----------------------------------------------------------------------
	@Override
	protected String getSqlSelect() {
		return SQL_SELECT ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlInsert() {
		return SQL_INSERT ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlUpdate() {
		return SQL_UPDATE ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlDelete() {
		return SQL_DELETE ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlCount() {
		return SQL_COUNT ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlCountAll() {
		return SQL_COUNT_ALL ;
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, Emp emp) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, emp.getEmpno() ) ; // "EMPNO" : int
	}

    //----------------------------------------------------------------------
	@Override
	protected Emp populateBean(ResultSet rs, Emp emp) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		emp.setEmpno(rs.getInt("EMPNO")); // int
		emp.setEname(rs.getString("ENAME")); // java.lang.String
		emp.setJob(rs.getString("JOB")); // java.lang.String
		emp.setAge(rs.getInt("AGE")); // int
		emp.setSal(rs.getInt("SAL")); // int
		emp.setDeptno(rs.getInt("DEPTNO")); // int
		return emp ;
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, Emp emp) throws SQLException {

		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		// "EMPNO" is auto-incremented => no set in insert		
		setValue(ps, i++, emp.getEname() ) ; // "ENAME" : java.lang.String
		setValue(ps, i++, emp.getJob() ) ; // "JOB" : java.lang.String
		setValue(ps, i++, emp.getAge() ) ; // "AGE" : int
		setValue(ps, i++, emp.getSal() ) ; // "SAL" : int
		setValue(ps, i++, emp.getDeptno() ) ; // "DEPTNO" : int
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, Emp emp) throws SQLException {

		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, emp.getEname() ) ; // "ENAME" : java.lang.String
		setValue(ps, i++, emp.getJob() ) ; // "JOB" : java.lang.String
		setValue(ps, i++, emp.getAge() ) ; // "AGE" : int
		setValue(ps, i++, emp.getSal() ) ; // "SAL" : int
		setValue(ps, i++, emp.getDeptno() ) ; // "DEPTNO" : int
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, emp.getEmpno() ) ; // "EMPNO" : int
	}

}