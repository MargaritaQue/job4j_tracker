package ru.job4j.bank;

import ru.job4j.bank.Account;
import ru.job4j.bank.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает модель банковской системы
 * В системе можно производить следующие действия.
 * 1. Регистрировать пользователя.
 * 2. Удалять пользователя из системы.
 * 3. Добавлять пользователю банковский счет. У пользователя системы могут быть несколько счетов.
 * 4. Переводить деньги с одного банковского счета на другой счет.
 * @author Margarita
 * @version 1.0
 */
public class BankService {
    /**
     * Поле содержит всех пользователей системы с привязанныи к ним счетами.
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод принимает и добавляет пользователя в систему.
     * Добавляет пустой список счетов.
     * @param user пользователь, которого нужно добавить
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Метод удаляет пользователя из сисетемы по паспорту.
     * @param passport паспорт пользователя
     */
    public void deleteUser(String passport) {
        users.remove(findByPassport(passport));
    }

    /**
     * Метод добавляет новый счет к пользователю.
     * Ищет пользователя по паспорту, получает список всех счетов и добавляет новый счет.
     * Проверяет, что такого счета у пользователя еще нет.
     * @param passport паспорт пользователя
     * @param account счета пользователя
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if ((user != null) && (!users.get(user).contains(account))) {
            users.get(user).add(account);
        }
    }

    /**
     * Метод ищет пользователя по номеру паспорта.
     * Перебирает все элементы поля users и сравнивает с параметром passport.
     * @param passport паспорт пользователя
     * @return возвращает пользователя
     */
    public User findByPassport(String passport) {
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Метод ищет счет пользователя по реквизитам.
     * Ищет пользователя по параметру passport с помощью метода findByPassport.
     * Если нашел, то перебирает счета пользователя и сравнивает реквизыты с параметром requisite.
     * @param passport паспорт пользователя
     * @param requisite реквизиты счета
     * @return счет пользователя.
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            for (Account acc : users.get(user)) {
                if (acc.getRequisite().equals(requisite)) {
                    return acc;
                }
            }
        }
        return null;
    }

    /**
     * Метод предназначен для перечисления денег с одного счёта на другой счёт.
     * Ищет счета отправителя и получателя по паспортам и реквезитам.
     * Делает проверку, что счета найдены.
     * Делает проверку, что у отправиеля достаточно средств на счету.
     * Добавляет к счет получателя отправляемую сумму.
     * Убирает у отправителя отправляемую сумму.
     * @param sourcePassport паспорт отправителя
     * @param sourceRequisite реквизиты счета отправителя
     * @param destinationPassport паспорт получателя
     * @param destinationRequisite реквизиты счета получателя
     * @param amount отправляемая денежная сумма
     * @return false, если средства не отправились, true, если отправились
     */
    public boolean transferMoney(String sourcePassport, String sourceRequisite,
                                 String destinationPassport, String destinationRequisite,
                                 double amount) {
        boolean result = false;
        Account sourceAccount = findByRequisite(sourcePassport, sourceRequisite);
        Account destinationAccount = findByRequisite(destinationPassport, destinationRequisite);
        if ((destinationAccount != null) && (sourceAccount != null)
                && (sourceAccount.getBalance() >= amount)) {
            destinationAccount.setBalance(destinationAccount.getBalance() + amount);
            sourceAccount.setBalance(sourceAccount.getBalance() - amount);
            return true;
        }
        return result;
    }

    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}