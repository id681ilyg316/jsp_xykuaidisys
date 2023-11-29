package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

/**
 * ����Apache dbutils�������ݿ�Ĺ�����,�ɲ���Springע��DataSource
 * 
 * <pre>
 * ��ʽһ:ʹ��Ĭ�ϵ�����Դ(jdbc/eBuilder)
 *    DBUtil tool=new DBUtil();///�������Ĭ�Ϲر�����
 *    DBUtil tool=new DBUtil(false);//���ò��ر�����
 *    ʹ����֮��ر����� tool.close();
 * ��ʽ��:ʹ���Զ�������Դ
 *    String dataSourceName=&quot;&quot;;
 *    DBUtil tool=new DBUtil(dataSourceName);///�������Ĭ�Ϲر�����
 *    DBUtil tool=new DBUtil(dataSourceName,false);//������ϲ��ر�����
 *    ʹ����֮��ر����� tool.close();
 * ��ʽ��:ʹ���Զ�������Դ
 *    java.sql.DataSource dataSource=null;
 *    DBUtil tool=new DBUtil(dataSource);///�������Ĭ�Ϲر�����
 *    DBUtil tool=new DBUtil(dataSource,false);//������ϲ��ر�����
 *    ʹ����֮��ر����� tool.close();
 * ��ʽ��:ʹ��ָ�������ݿ�����
 *    java.sql.connection=null;
 *    DBUtil tool=new DBUtil(connection);///�������Ĭ�Ϲر�����
 *    DBUtil tool=new DBUtil(dataSource,false);//������ϲ��ر�����
 *    ʹ����֮��ر����� tool.close();
 * </pre>
 * 
 */
public class DBUtil {
	private static final String dbuser = "xykuaidisys";
	private static final String dbpassword = "xykuaidisys";
	private static final String dburl = "jdbc:mysql://www.icodedock.com/xykuaidisys?useSSL=false&serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&allowPublicKeyRetrieval=true";
	// Spring��ע��DataSource
	public DataSource dataSource = null;
	private Connection connection = null;
	// ***********��̬����
	private boolean CloseConnection = true;
	public static final String DEFAULT_JNDI = "jdbc/eBuilder";

	/**
	 * ����Ĭ�����ݿ�����(jdbc/eBuilder)�������Ĭ�Ϲر�����
	 */
	public DBUtil() {
	}

	/**
	 * ����Ĭ�����ݿ�����(jdbc/eBuilder)��ָ���Ƿ������Ϲر�����
	 */
	public DBUtil(boolean closeConnection) {
		CloseConnection = closeConnection;
	}

	/**
	 * ����ָ�������ݿ����ӣ��������Ĭ�Ϲر�����
	 * 
	 * @param connection
	 */
	public DBUtil(Connection connection) {
		this.connection = connection;
	}

	/**
	 * ����ָ�������ݿ����ӣ���ָ���Ƿ������Ϲر�����
	 * 
	 * @param connection
	 * @param closeConnection
	 */
	public DBUtil(Connection connection, boolean closeConnection) {
		this.connection = connection;
		CloseConnection = closeConnection;
	}

	/**
	 * ����ָ����DataSource�������ݿ⣬�������Ĭ�Ϲر�����
	 * 
	 * @param dataSource
	 */
	public DBUtil(DataSource dataSource) {
		try {
			this.dataSource = dataSource;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����ָ����DataSource�������ݿ⣬��ָ���Ƿ������Ϲر�����
	 * 
	 * @param dataSource
	 * @param closeConnection
	 */
	public DBUtil(DataSource dataSource, boolean closeConnection) {
		try {
			this.dataSource = dataSource;
			CloseConnection = closeConnection;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Execute a batch of SQL INSERT, UPDATE, or DELETE queries.
	 * 
	 * @param sql
	 *            The SQL to execute.
	 * @param params
	 *            An array of query replacement parameters. Each row in this
	 *            array is one set of batch replacement values.
	 * @return The number of rows updated per statement.
	 */
	public int[] batch(String sql, Object[][] params) throws Exception {
		Connection conn = this.getConnection();
		int[] rows = null;
		QueryRunner run = new QueryRunner();
		try {
			rows = run.batch(conn, sql, params);
		} finally {
			close(conn);
		}

		return rows;
	}

	/**
	 * �ر�����
	 * 
	 * @param connection
	 */
	public void close() {
		DbUtils.closeQuietly(connection);
	}

	private void close(Connection connection) {
		if (CloseConnection) {
			DbUtils.closeQuietly(connection);
		}
	}

	/**
	 * Executes the given INSERT, UPDATE, or DELETE SQL statement without any
	 * replacement parameters.
	 * 
	 * @param sql
	 *            The SQL statement to execute.
	 * @return The number of rows updated.
	 */
	public int executeUpdate(String sql) throws Exception {
		Connection conn = this.getConnection();
		int rows = 0;
		QueryRunner run = new QueryRunner();
		try {
			rows = run.update(conn, sql);
		} finally {
			close(conn);
		}
		return rows;
	}

	/**
	 * Executes the given INSERT, UPDATE, or DELETE SQL statement with a single
	 * replacement parameter.
	 * 
	 * @param sql
	 *            The SQL statement to execute.
	 * @param param
	 *            The replacement parameter.
	 * @return The number of rows updated.
	 */

	public int executeUpdate(String sql, Object param) throws Exception {
		Connection conn = this.getConnection();
		int rows = 0;
		QueryRunner run = new QueryRunner();
		try {
			rows = run.update(conn, sql, param);
		} finally {
			close(conn);
		}

		return rows;
	}

	/**
	 * Executes the given INSERT, UPDATE, or DELETE SQL statement.
	 * 
	 * @param sql
	 *            The SQL statement to execute.
	 * @param params
	 *            Initializes the PreparedStatement's IN (i.e. '?') parameters.
	 * @return The number of rows updated.
	 */

	public int executeUpdate(String sql, Object[] params) throws Exception {
		System.out.println(sql);
		Connection conn = this.getConnection();
		int rows = 0;
		QueryRunner run = new QueryRunner();
		try {
			rows = run.update(conn, sql, params);
		} finally {
			close(conn);
		}

		return rows;
	}

	/**
	 * ������ݿ������
	 * 
	 * @return Connection
	 */
	public Connection getConnection() throws Exception {
		// ���ConnectionΪ��
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(dburl, dbuser, dbpassword);
		if (connection == null || connection.isClosed()) {
			// ���dataSourceû��ע��Ͳ���Ĭ�ϵ�
			if (dataSource == null) {
				dataSource = (DataSource) new InitialContext()
						.lookup(DEFAULT_JNDI);
			}
			connection = dataSource.getConnection();
		}
		return connection;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * Get Maximun Field Value Named 'sFldName' of Table 'sTblName'
	 * 
	 * @param sTblName
	 *            The Table Name
	 * @param sFldName
	 *            The Field Name
	 * @return Maximum Id
	 */
	@SuppressWarnings({ "rawtypes" })
	public int getMaxId(String sTableName, String sFieldName) {
		int iRes = -1;
		String sql = "SELECT MAX(" + sFieldName + ") AS maxid FROM "
				+ sTableName;
		Map result;
		try {
			result = queryToMap(sql);
			if (result.get("maxid") != null) {
				iRes = Integer.parseInt(result.get("maxid").toString()) + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iRes;
	}

	/**
	 * Execute an SQL SELECT query without any replacement parameters and place
	 * the column values from the first row in an Object[]. Usage Demo:
	 * 
	 * <pre>
	 * Object[] result = searchToArray(sql);
	 * if (result != null) {
	 * 	for (int i = 0; i &lt; result.length; i++) {
	 * 		System.out.println(result[i]);
	 * 	}
	 * }
	 * </pre>
	 * 
	 * @param sql
	 *            The SQL to execute.
	 * @return An Object[] or null if there are no rows in the ResultSet.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object[] queryToArray(String sql) throws Exception {
		Connection conn = this.getConnection();
		Object[] result = null;
		QueryRunner run = new QueryRunner();
		ResultSetHandler h = new ArrayHandler();
		try {
			result = (Object[]) run.query(conn, sql, h);
		} finally {
			close(conn);
		}

		return result;
	}

	/**
	 * Executes the given SELECT SQL with a single replacement parameter and
	 * place the column values from the first row in an Object[].
	 * 
	 * @param sql
	 *            The SQL statement to execute.
	 * @param param
	 *            The replacement parameter.
	 * @return An Object[] or null if there are no rows in the ResultSet.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object[] queryToArray(String sql, Object param) throws Exception {
		Connection conn = this.getConnection();
		Object[] result = null;
		QueryRunner run = new QueryRunner();
		ResultSetHandler h = new ArrayHandler();
		try {
			result = (Object[]) run.query(conn, sql, h, param);
		} finally {
			close(conn);
		}

		return result;
	}

	/**
	 * Executes the given SELECT SQL query and place the column values from the
	 * first row in an Object[].
	 * 
	 * @param sql
	 *            The SQL statement to execute.
	 * @param params
	 *            Initialize the PreparedStatement's IN parameters with this
	 *            array.
	 * @return An Object[] or null if there are no rows in the ResultSet.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object[] queryToArray(String sql, Object[] params) throws Exception {
		Connection conn = this.getConnection();
		Object[] result = null;
		QueryRunner run = new QueryRunner();
		ResultSetHandler h = new ArrayHandler();
		try {
			result = (Object[]) run.query(conn, sql, h, params);
		} finally {
			close(conn);
		}
		return result;
	}

	/**
	 * Execute an SQL SELECT query without any replacement parameters and place
	 * the ResultSet into a List of Object[]s Usage Demo:
	 * 
	 * <pre>
	 * List result = queryToList(sql);
	 * Iterator iterator = result.iterator();
	 * while (iterator.hasNext()) {
	 * 	Object[] temp = (Object[]) iterator.next();
	 * 	for (int i = 0; i &lt; temp.length; i++) {
	 * 		System.out.println(temp[i]);
	 * 	}
	 * }
	 * </pre>
	 * 
	 * @param sql
	 *            The SQL statement to execute.
	 * @return A List of Object[]s, never null.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", })
	public List queryToList(String sql) throws Exception {
		Connection conn = this.getConnection();
		List result = null;
		QueryRunner run = new QueryRunner();
		ResultSetHandler h = new ArrayListHandler();
		try {
			result = (List) run.query(conn, sql, h);
		} finally {
			close(conn);
		}
		return result;
	}

	/**
	 * Executes the given SELECT SQL with a single replacement parameter and
	 * place the ResultSet into a List of Object[]s
	 * 
	 * @param sql
	 *            The SQL statement to execute.
	 * @param param
	 *            The replacement parameter.
	 * @return A List of Object[]s, never null.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List queryToList(String sql, Object param) throws Exception {
		Connection conn = this.getConnection();
		List result = null;
		QueryRunner run = new QueryRunner();
		ResultSetHandler h = new ArrayListHandler();
		try {
			result = (List) run.query(conn, sql, h, param);
		} finally {
			close(conn);
		}

		return result;
	}

	/**
	 * Executes the given SELECT SQL query and place the ResultSet into a List
	 * of Object[]s
	 * 
	 * @param sql
	 *            The SQL statement to execute.
	 * @param params
	 *            Initialize the PreparedStatement's IN parameters with this
	 *            array.
	 * @return A List of Object[]s, never null.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List queryToList(String sql, Object[] params) throws Exception {
		Connection conn = this.getConnection();
		List result = null;
		QueryRunner run = new QueryRunner();
		ResultSetHandler h = new ArrayListHandler();
		try {

			result = (List) run.query(conn, sql, h, params);

		} finally {
			close(conn);
		}

		return result;
	}

	/**
	 * Execute an SQL SELECT query without any replacement parameters and
	 * converts the first ResultSet into a Map object. Usage Demo:
	 * 
	 * <pre>
	 * Map result = queryToMap(sql);
	 * System.out.println(map.get(columnName));
	 * </pre>
	 * 
	 * @param sql
	 *            The SQL to execute.
	 * @return A Map with the values from the first row or null if there are no
	 *         rows in the ResultSet.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map queryToMap(String sql) throws Exception {
		Connection conn = this.getConnection();
		Map result = null;
		QueryRunner run = new QueryRunner();
		ResultSetHandler h = new MapHandler();
		try {

			result = (Map) run.query(conn, sql, h);

		} finally {
			close(conn);
		}

		return result;
	}

	/**
	 * Executes the given SELECT SQL with a single replacement parameter and
	 * converts the first ResultSet into a Map object.
	 * 
	 * @param sql
	 *            The SQL to execute.
	 * @param param
	 *            The replacement parameter.
	 * @return A Map with the values from the first row or null if there are no
	 *         rows in the ResultSet.
	 */
	@SuppressWarnings({ "unchecked",  "rawtypes" })
	public Map queryToMap(String sql, Object param) throws Exception {
		Connection conn = this.getConnection();
		Map result = null;
		QueryRunner run = new QueryRunner();
		ResultSetHandler h = new MapHandler();
		try {
			result = (Map) run.query(conn, sql,h, param);

		} finally {
			close(conn);
		}
		return result;
	}

	/**
	 * Executes the given SELECT SQL query and converts the first ResultSet into
	 * a Map object.
	 * 
	 * @param sql
	 *            The SQL to execute.
	 * @param params
	 *            Initialize the PreparedStatement's IN parameters with this
	 *            array.
	 * @return A Map with the values from the first row or null if there are no
	 *         rows in the ResultSet.
	 */
	@SuppressWarnings({ "unchecked",  "rawtypes" })
	public Map queryToMap(String sql, Object[] params) throws Exception {
		Connection conn = this.getConnection();
		Map result = null;
		QueryRunner run = new QueryRunner();
		ResultSetHandler h = new MapHandler();
		try {

			result = (Map) run.query(conn, sql, h,params);

		} finally {
			close(conn);
		}

		return result;
	}

	/**
	 * Execute an SQL SELECT query without any replacement parameters and
	 * converts the ResultSet into a List of Map objects. Usage Demo:
	 * 
	 * <pre>
	 * List result = queryToMapList(sql);
	 * Iterator iterator = result.iterator();
	 * while (iterator.hasNext()) {
	 * 	Map map = (Map) iterator.next();
	 * 	System.out.println(map.get(columnName));
	 * }
	 * </pre>
	 * 
	 * @param sql
	 *            The SQL to execute.
	 * @return A List of Maps, never null.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List queryToMapList(String sql) throws Exception {
		Connection conn = this.getConnection();
		List result = null;
		QueryRunner run = new QueryRunner();
		ResultSetHandler h = new MapListHandler();
		try {

			result = (List) run.query(conn, sql, h);

		} finally {
			close(conn);
		}

		return result;
	}

	/**
	 * Executes the given SELECT SQL with a single replacement parameter and
	 * converts the ResultSet into a List of Map objects.
	 * 
	 * @param sql
	 *            The SQL to execute.
	 * @param param
	 *            The replacement parameter.
	 * @return A List of Maps, never null.
	 */
	@SuppressWarnings({ "unchecked",  "rawtypes" })
	public List queryToMapList(String sql, Object param) throws Exception {
		Connection conn = this.getConnection();
		List result = null;
		QueryRunner run = new QueryRunner();
		ResultSetHandler h = new MapListHandler();
		try {
			result = (List) run.query(conn, sql, h,param);
		} finally {
			close(conn);
		}

		return result;
	}

	/**
	 * Executes the given SELECT SQL query and converts the ResultSet into a
	 * List of Map objects.
	 * 
	 * @param sql
	 *            The SQL to execute.
	 * @param params
	 *            Initialize the PreparedStatement's IN parameters with this
	 *            array.
	 * @return A List of Maps, never null.
	 */
	@SuppressWarnings({ "unchecked",  "rawtypes" })
	public List queryToMapList(String sql, Object[] params) throws Exception {
		Connection conn = this.getConnection();
		List result = null;
		QueryRunner run = new QueryRunner();
		ResultSetHandler h = new MapListHandler();
		try {

			result = (List) run.query(conn, sql, h,params);

		} finally {
			close(conn);
		}

		return result;
	}

	/**
	 * Execute an SQL SELECT query without any replacement parameters and
	 * Convert the first row of the ResultSet into a bean with the Class given
	 * in the parameter. Usage Demo:
	 * 
	 * <pre>
	 * String sql = &quot;SELECT * FROM test&quot;;
	 * Test test = (Test) queryToBean(Test.class, sql);
	 * if (test != null) {
	 * 	System.out.println(&quot;test:&quot; + test.getPropertyName());
	 * }
	 * </pre>
	 * 
	 * @param type
	 *            The Class of beans.
	 * @param sql
	 *            The SQL to execute.
	 * @return An initialized JavaBean or null if there were no rows in the
	 *         ResultSet.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object queryToBean(Class type, String sql) throws Exception {
		Connection conn = this.getConnection();
		Object result = null;
		QueryRunner run = new QueryRunner();
		ResultSetHandler h = new BeanHandler(type);
		try {
			result = run.query(conn, sql, h);
		} finally {
			close(conn);
		}

		return result;
	}

	/**
	 * Executes the given SELECT SQL with a single replacement parameter and
	 * Convert the first row of the ResultSet into a bean with the Class given
	 * in the parameter.
	 * 
	 * @param type
	 *            The Class of beans.
	 * @param sql
	 *            The SQL to execute.
	 * @param param
	 *            The replacement parameter.
	 * @return An initialized JavaBean or null if there were no rows in the
	 *         ResultSet.
	 */
	@SuppressWarnings({ "unchecked",   "rawtypes" })
	public Object queryToBean(Class type, String sql, Object param)
			throws Exception {
		Connection conn = this.getConnection();
		Object result = null;
		QueryRunner run = new QueryRunner();
		ResultSetHandler h = new BeanHandler(type);
		try {

			result = run.query(conn, sql, h,param);

		} finally {
			close(conn);
		}

		return result;
	}

	/**
	 * Executes the given SELECT SQL query and Convert the first row of the
	 * ResultSet into a bean with the Class given in the parameter.
	 * 
	 * @param type
	 *            The Class of beans.
	 * @param sql
	 *            The SQL to execute.
	 * @param params
	 *            Initialize the PreparedStatement's IN parameters with this
	 *            array.
	 * @return An initialized JavaBean or null if there were no rows in the
	 *         ResultSet.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object queryToBean(Class type, String sql, Object[] params)
			throws Exception {
		Connection conn = this.getConnection();
		Object result = null;
		QueryRunner run = new QueryRunner();
		ResultSetHandler h = new BeanHandler(type);
		try {
			// result = run.query(conn, sql, params, h);
			result = run.query(conn, sql, h, params);
		} finally {
			close(conn);
		}
		return result;
	}

	/**
	 * Execute an SQL SELECT query without any replacement parameters and
	 * Convert the ResultSet rows into a List of beans with the Class given in
	 * the parameter. Usage Demo:
	 * 
	 * <pre>
	 * List result = queryToBeanList(Test.class, sql);
	 * Iterator iterator = result.iterator();
	 * while (iterator.hasNext()) {
	 * 	Test test = (Test) iterator.next();
	 * 	System.out.println(test.getPropertyName());
	 * }
	 * </pre>
	 * 
	 * @param type
	 *            The Class that objects returned from handle() are created
	 *            from.
	 * @param sql
	 *            The SQL to execute.
	 * @return A List of beans (one for each row), never null.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", })
	public List queryToBeanList(Class type, String sql) throws Exception {
		Connection conn = this.getConnection();
		List result = null;
		QueryRunner run = new QueryRunner();
		ResultSetHandler h = new BeanListHandler(type);
		try {
			result = (List) run.query(conn, sql, h);
		} finally {
			close(conn);
		}
		return result;
	}

	/**
	 * Executes the given SELECT SQL with a single replacement parameter and
	 * Convert the ResultSet rows into a List of beans with the Class given in
	 * the parameter.
	 * 
	 * @param type
	 *            The Class that objects returned from handle() are created
	 *            from.
	 * @param sql
	 *            The SQL to execute.
	 * @param param
	 *            The replacement parameter.
	 * @return A List of beans (one for each row), never null.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List queryToBeanList(Class type, String sql, Object param)
			throws Exception {
		Connection conn = this.getConnection();
		List result = null;
		QueryRunner run = new QueryRunner();
		ResultSetHandler h = new BeanListHandler(type);
		try {

			result = (List) run.query(conn, sql, h, param);

		} finally {
			close(conn);
		}

		return result;
	}

	/**
	 * Executes the given SELECT SQL query and Convert the ResultSet rows into a
	 * List of beans with the Class given in the parameter.
	 * 
	 * @param type
	 *            The Class that objects returned from handle() are created
	 *            from.
	 * @param sql
	 *            The SQL to execute.
	 * @param params
	 *            Initialize the PreparedStatement's IN parameters with this
	 *            array.
	 * @return A List of beans (one for each row), never null.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List queryToBeanList(Class type, String sql, Object[] params)
			throws Exception {
		Connection conn = this.getConnection();
		List result = null;
		QueryRunner run = new QueryRunner();
		ResultSetHandler h = new BeanListHandler(type);
		try {
			result = (List) run.query(conn, sql, h, params);
		} finally {
			close(conn);
		}

		return result;
	}
	 

	/**
	 * �ع�����عر�����
	 * 
	 * @param connection
	 */
	public static void rolbackAndClose(Connection connection) {
		try {
			DbUtils.rollbackAndClose(connection);
		} catch (Exception e) {
		}
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public static void main(String[] args) {
		Connection connection = null;
		try {
			DBUtil tool = new DBUtil(false);
			connection = tool.getConnection();
			System.out.println("step1:�����Ƿ��Ѿ����?");
			System.out
					.println(((connection != null) || (!connection.isClosed())));

			System.out.println("\nstep2:���Թرձ�������...\nconnection.close();");
			connection.close();

			System.out.println("\nstep3:���������Ƿ�ر�?\t" + (connection.isClosed()));
			System.out.println("DBUtil�е������Ƿ�ر�?\t");
			tool.conncectionState();

			System.out.println("\nstep4:�رձ�������֮��DBUtil�е������Ƿ�رգ�");
			tool.conncectionState();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
			}
			System.out.println("\nstep5:�������ݿ��������!");
		}
	}

	public void conncectionState() {
		try {
			System.out.println("�����Ƿ�Ϊ��:\t" + (connection == null));
			System.out.println("�����Ƿ�ر�:\t" + (connection.isClosed()));
		} catch (Exception e) {
		}
	}
}