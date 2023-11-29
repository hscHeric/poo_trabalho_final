public class ModUserRepository extends StdUserRepository{
    public ModUserRepository() {
        super();   
    }

    public void deletePost(Session session, String title) {
        if(session.isLoggedIn()) {
            this._sRepository.removePostFromFeed(title);
            return;
        }
    }

    public void deleteUser(Session session, String username) {
        if(session.isLoggedIn()) {
            if(session.getAccount().getUsername().equals(username)) {
                throw new RuntimeException("Não é possível deletar a si mesmo");
            }
            this._sRepository.deleteUser(session.getAccount().getUsername(), username);
            return;
        }
    }
}
