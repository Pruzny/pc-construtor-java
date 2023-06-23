package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import excecao.InfraestruturaException;

public class JPAUtil {
	private static EntityManagerFactory emf;
	private static final ThreadLocal<EntityManager> threadEntityManager = new ThreadLocal<EntityManager>();
	private static final ThreadLocal<EntityTransaction> threadTransaction = new ThreadLocal<EntityTransaction>();

	private static final ThreadLocal<Integer> commitCounter = new ThreadLocal<Integer>() {
		public Integer initialValue() {
			return 0;
		}
	};

	static {
		try {
			emf = Persistence.createEntityManagerFactory("exercicio");
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println(">>>>>>>>>> Mensagem de erro: " + e.getMessage());
			throw e;
		}
	}

	public static void beginTransaction() { // System.out.println("Vai criar transacao");
		EntityTransaction tx = threadTransaction.get();
		Integer count = commitCounter.get();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Entrou em beginTransaction()");
		try {
			if (tx == null) {
				tx = getEntityManager().getTransaction();
				tx.begin();
				// System.out.println("Criou transacao");
			} else { // System.out.println("Nao criou transacao");
			}
			threadTransaction.set(tx);
		} catch (RuntimeException ex) {
			throw new InfraestruturaException(ex);
		} finally {
			commitCounter.set(count + 1);
		}
	}

	public static EntityManager getEntityManager() { // System.out.println("Abriu ou recuperou sess�o");

		EntityManager s = threadEntityManager.get();
		// Abre uma nova Sess�o, se a thread ainda n�o possui uma.
		try {
			if (s == null) {
				s = emf.createEntityManager();
				threadEntityManager.set(s);
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Criou o entity manager");
			}
		} catch (RuntimeException ex) {
			throw new InfraestruturaException(ex);
		}
		return s;
	}

	public static void commitTransaction() {
		EntityTransaction tx = threadTransaction.get();
		Integer count = commitCounter.get();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Entrou em commitTransaction");
		try {
			if (tx != null && tx.isActive()) {
				if (count - 1 == 0) {
					tx.commit();
					threadTransaction.set(null);
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Comitou transacao");
				}
			}
		} catch (RuntimeException ex) {
			System.out.println(6);
			try {
				rollbackTransaction();
			} catch (RuntimeException e) {
			}

			throw new InfraestruturaException(ex);
		} finally {
			commitCounter.set(count - 1);
		}
	}

	public static void rollbackTransaction() {
		System.out.println("Vai efetuar rollback de transacao");

		Integer count = commitCounter.get();
		EntityTransaction tx = threadTransaction.get();
		try {
			commitCounter.set(0);
			threadTransaction.set(null);
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} catch (RuntimeException ex) {
			throw new InfraestruturaException(ex);
		} finally {
			closeEntityManager();
		}
	}

	public static void closeEntityManager() { // System.out.println("Vai fechar sess�o");
		Integer count = commitCounter.get();
		if (count == 0) {
			try {
				EntityManager s = threadEntityManager.get();
				threadEntityManager.set(null);
				if (s != null && s.isOpen()) {
					s.close();
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Fechou o entity manager");
				}

				EntityTransaction tx = threadTransaction.get();
				if (tx != null && tx.isActive()) {
					rollbackTransaction();
					throw new RuntimeException("EntityManager sendo fechado " + "com transa��o ativa.");
				}
			} catch (RuntimeException ex) {
				throw new InfraestruturaException(ex);
			}
		}
	}
}
