package benchmark

import java.sql._
import com.jolbox.bonecp.BoneCPConfig
import com.jolbox.bonecp.BoneCPDataSource

object Test {
  val dataSource = {
    Class.forName("org.postgresql.Driver")
    val config = new BoneCPConfig
    config.setJdbcUrl("jdbc:postgresql://localhost/bonecptest")
    config.setUsername("bonecptest")
    config.setPassword("secret")
    config.setDefaultAutoCommit(true)
    config.setStatementsCacheSize(10)
    config.setMaxConnectionsPerPartition(10)
    config.setPartitionCount(1)
    new BoneCPDataSource(config)
  }

  def run = time {
    (1 to 100).par.foreach { _ =>
      (1 to 1000) foreach query
    }
  }

  def query(id: Int) = {
    val conn = dataSource.getConnection
    try {
      val stmt = conn.prepareStatement("SELECT balance FROM account WHERE id=?")
      stmt.setInt(1, id)
      withResultSet(stmt) { rs =>
        if (rs.next) Some(rs.getInt(1)) else None
      }
    } finally {
      conn.close
    }
  }

  def withResultSet[A](stmt: PreparedStatement)(f: ResultSet => A) = {
    var rs: ResultSet = null
    try {
      f(stmt.executeQuery)
    } finally {
      if (rs != null) try rs.close catch { case e: Exception => }
      stmt.close
    }
  }

  def time[A](a: => A) = {
    val start = System.currentTimeMillis
    val aa = a
    println((System.currentTimeMillis - start) + "ms")
    aa
  }
}
