package vendingmachine.controller;

import java.util.List;
import java.util.Map;
import vendingmachine.domain.Coin;
import vendingmachine.domain.Product;
import vendingmachine.domain.VendingMachine;
import vendingmachine.exception.ErrorMessage;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {

    private final InputView inputView;
    private final OutputView outputView;

    public VendingMachineController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        VendingMachine machine = initializeMachine();
        machine.putMoney(getMoney());
        while (true) {
            outputView.printCurrentMoney(machine.getMoney());

            if (machine.cantContinue()) {
                break;
            }

            purchaseProduct(machine);
        }
        outputView.printCharges(machine.returnCharge());
    }

    private void purchaseProduct(VendingMachine machine) {
        while (true) {
            outputView.printProductNameRequest();
            try {
                String productName = inputView.readProductName();
                if (!machine.isExistProduct(productName)) {
                    throw new IllegalArgumentException(ErrorMessage.PRODUCT_NOT_FOUND.getMessage());
                }
                machine.sold(productName);
                return;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private VendingMachine initializeMachine() {
        int machineAmount = getMachineAmount();
        Map<Coin, Integer> coins = Coin.makeCoins(machineAmount);
        outputView.printMachineCoins(coins);
        List<Product> products = getProducts();
        return new VendingMachine(coins, products);
    }

    private int getMoney() {
        while (true) {
            outputView.printPutMoneyRequest();
            try {
                return inputView.readMoney();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<Product> getProducts() {
        while (true) {
            outputView.printRegisterProductsRequest();
            try {
                return inputView.readRegisterProducts();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private int getMachineAmount() {
        while (true) {
            outputView.printMachineAmountRequest();
            try {
                return inputView.readMachineAmount();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
