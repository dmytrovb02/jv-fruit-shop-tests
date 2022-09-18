package core.basesyntax.strategy.operations.impl;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.operations.OperationHandler;

public class ReturnOperationHandlerImpl implements OperationHandler {
    private StorageDao storageDao;

    public ReturnOperationHandlerImpl(StorageDao storageDao) {
        this.storageDao = storageDao;
    }

    public void handler(FruitTransaction fruitTransaction) {
        storageDao.update(fruitTransaction.getFruit(),
                storageDao.remainder(fruitTransaction.getFruit())
                        + fruitTransaction.getQuantity());
    }
}