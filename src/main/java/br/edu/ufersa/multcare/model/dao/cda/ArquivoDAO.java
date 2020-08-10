package br.edu.ufersa.multcare.model.dao.cda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.edu.ufersa.multcare.model.bean.cda.Arquivo;
import br.edu.ufersa.multcare.model.dao.cda.ConnectionFactory;

/**
 *
 * @author Gyovanne Cavalcanti
 */
public class ArquivoDAO {

    private Connection con = null;

    /**
     *
     */
    public ArquivoDAO() {
        con = ConnectionFactory.getConnection();
    }

    /**
     *
     * @param idArquivoUsuario
     * @param idArquivo
     * @return
     */
    public boolean duplicarDocumentoClinico(int idArquivoUsuario, int idArquivo) {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO arquivos (idUsuario, nome, dataModificacao, arquivo,versao) SELECT ?, nome, dataModificacao, arquivo, versao FROM arquivos WHERE id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idArquivoUsuario);
            stmt.setInt(2, idArquivo);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     *
     * @param idArquivo
     * @param idArquivoUsuario
     * @param op
     * @return
     */
    public boolean deletarDocumentoClinico(int idArquivo, int idArquivoUsuario, int op) {
        PreparedStatement stmt = null;
        String sql = (op == 1) ? "DELETE FROM arquivos WHERE idUsuario = ? and id = ?" : "DELETE FROM bkArquivos WHERE idUsuario = ? and id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idArquivoUsuario);
            stmt.setInt(2, idArquivo);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /**
     *
     * @param idUsuario
     * @param op
     * @return
     */
    public List<Arquivo> listarDocumentosClinicos(int idUsuario, int op) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Arquivo> arquivos = new ArrayList<>();
        String sql = (op == 1) ? "SELECT * FROM arquivos WHERE idUsuario = ?" : "SELECT * FROM bkArquivos WHERE idUsuario = ? order by versao desc";
        try {
            //or cidade LIKE ? or diagnostico LIKE ?
            stmt = con.prepareStatement(sql); // busca sem restricoes
            stmt.setInt(1, idUsuario);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Arquivo arquivo = new Arquivo();
                arquivo.setIdArquivo(rs.getInt("id"));
                arquivo.setNomeArquivo(rs.getString("nome"));
                arquivo.setDataArquivo(rs.getString("dataModificacao"));
                arquivo.setFileArquivo(rs.getBytes("arquivo"));
                arquivo.setVersaoArquivo(rs.getDouble("versao"));
                arquivos.add(arquivo);
            }
        } catch (SQLException ex) {
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return arquivos;
    }

    /**
     *
     * @param idArquivo
     * @param idArquivoUsuario
     * @param op
     * @return
     */
    public Arquivo buscarDocumentoClinico(int idArquivo, int idArquivoUsuario, int op) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Arquivo arquivo = new Arquivo();
        String sql = (op == 1) ? "SELECT * FROM arquivos WHERE idUsuario = ? and id = ?" : "SELECT * FROM bkArquivos WHERE idUsuario = ? and id = ?";
        try {
            //or cidade LIKE ? or diagnostico LIKE ?
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idArquivoUsuario);
            stmt.setInt(2, idArquivo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                arquivo.setIdArquivo(rs.getInt("id"));
                arquivo.setNomeArquivo(rs.getString("nome"));
                arquivo.setDataArquivo(rs.getString("dataModificacao"));
                arquivo.setFileArquivo(rs.getBytes("arquivo"));
                arquivo.setVersaoArquivo(rs.getDouble("versao"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return arquivo;
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean copiarDocumentoClinico(int id) {
        String sql = "INSERT INTO bkArquivos (idUsuario, nome, dataModificacao, arquivo, versao) SELECT idUsuario, nome, dataModificacao, arquivo, versao FROM arquivos WHERE id = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, ps);
        }
    }

    /**
     *
     * @param arquivo
     * @return
     */
    public boolean adicionarDocumentoClinico(Arquivo arquivo) {
        String sql= "insert into arquivos (idUsuario, nome, dataModificacao, arquivo, versao)values(?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, arquivo.getIdUsuarioArquivo());
            ps.setString(2, arquivo.getNomeArquivo());
            ps.setString(3, arquivo.getDataArquivo());
            ps.setBytes(4, arquivo.getFileArquivo());
            ps.setDouble(5, arquivo.getVersaoArquivo());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, ps);
        }
    }

    /**
     *
     * @param arquivo
     * @param file
     * @return
     */
    public boolean atualizarDocumentoClinico(Arquivo arquivo, Arquivo file) {
        String sql = "UPDATE arquivos SET idUsuario = ?, nome = ?, dataModificacao = ?, arquivo = ?, versao = ? WHERE id = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, arquivo.getIdUsuarioArquivo());
            ps.setString(2, arquivo.getNomeArquivo());
            ps.setString(3, arquivo.getDataArquivo());
            ps.setBytes(4, arquivo.getFileArquivo());
            ps.setDouble(5, arquivo.getVersaoArquivo());

            ps.setInt(6, file.getIdArquivo());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, ps);
        }
    }
}
