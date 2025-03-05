# NicksAPPS

## Solidity
### Setup Foundry
Install Foundry using the instructions in [Foundry Book](https://book.getfoundry.sh/getting-started/installation).

Then, install the foundry packages used in this library using:
```bash
git submodule init
git submodule update
```

You can test the contracts in this library by running `forge test`.

### Blacklistable
Allows addresses to be blacklisted by a contract owner

### BlacklistableUpgradeable
Blacklistable for upgradeable contracts