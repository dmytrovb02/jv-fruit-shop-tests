package core.basesyntax.strategy;

import static org.junit.Assert.assertEquals;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.operations.OperationHandler;
import core.basesyntax.strategy.operations.impl.BalanceOperationHandlerImpl;
import core.basesyntax.strategy.operations.impl.PurchaseOperationHandlerImpl;
import core.basesyntax.strategy.operations.impl.ReturnOperationHandlerImpl;
import core.basesyntax.strategy.operations.impl.SupplyOperationHandlerImpl;
import java.util.HashMap;
import java.util.Map;
import org.junit.BeforeClass;
import org.junit.Test;

public class StrategyImplTest {
    private static StrategyImpl operationStrategy;

    @BeforeClass
    public static void beforeClass() {
        StorageDao storageDao = new StorageDaoImpl();
        Map<FruitTransaction.Operation, OperationHandler> operationHandlerMap =
                new HashMap<>();
        operationHandlerMap.put(FruitTransaction.Operation.BALANCE,
                new BalanceOperationHandlerImpl(storageDao));
        operationHandlerMap.put(FruitTransaction.Operation.PURCHASE,
                new PurchaseOperationHandlerImpl(storageDao));
        operationHandlerMap.put(FruitTransaction.Operation.SUPPLY,
                new SupplyOperationHandlerImpl(storageDao));
        operationHandlerMap.put(FruitTransaction.Operation.RETURN,
                new ReturnOperationHandlerImpl(storageDao));
        operationStrategy = new StrategyImpl(operationHandlerMap);
    }

    @Test
    public void get_balanceOperation_ok() {
        assertEquals(
                operationStrategy.get(FruitTransaction.Operation.BALANCE).getClass(),
                BalanceOperationHandlerImpl.class);
    }

    @Test
    public void get_purchaseOperation_ok() {
        assertEquals(
                operationStrategy.get(FruitTransaction.Operation.PURCHASE).getClass(),
                PurchaseOperationHandlerImpl.class);
    }

    @Test
    public void get_supplyOperation_ok() {
        assertEquals(operationStrategy.get(FruitTransaction.Operation.SUPPLY)
                        .getClass(),
                SupplyOperationHandlerImpl.class);
    }

    @Test
    public void get_returnOperation_ok() {
        assertEquals(operationStrategy.get(FruitTransaction.Operation.RETURN)
                        .getClass(),
                ReturnOperationHandlerImpl.class);
    }
}
