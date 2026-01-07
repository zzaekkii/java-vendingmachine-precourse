package vendingmachine.controller;

import java.util.Map;
import vendingmachine.domain.Coin;
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
        int machineAmount = getMachineAmount();
        Map<Coin, Integer> coins = Coin.makeCoins(machineAmount);

        outputView.printMachineCoins(coins);

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
