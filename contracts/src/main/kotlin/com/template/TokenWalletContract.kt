package com.template

import com.r3.corda.lib.tokens.contracts.EvolvableTokenContract
import com.template.states.TokenWalletState
import net.corda.core.contracts.*
import net.corda.core.transactions.LedgerTransaction

class TokenWalletContract: Contract {
    companion object{
        const val ID = "com.template.TokenWalletContract"
    }

    interface Commands : CommandData {
        class Register : TypeOnlyCommandData(), Commands
        class Preorder : TypeOnlyCommandData(), Commands
        class Issue : TypeOnlyCommandData(), Commands
        class Transfer : TypeOnlyCommandData(), Commands
        class Move : TypeOnlyCommandData(), Commands
    }

    override fun verify(tx: LedgerTransaction) {
        val command = tx.commands.requireSingleCommand<Commands>()

        when (command.value){
            is Commands.Register -> requireThat {
                "There are no inputs." using (tx.inputs.isEmpty())
                "Only one output state should be created" using (tx.outputs.size == 1)
                val outputRegister = tx.outputsOfType<TokenWalletState>().single()
                "Must be signed by the Registering node" using (command.signers.toSet() == outputRegister.participants.map { it.owningKey }.toSet())
                "Password must contain at least 6 characters with at least one Uppercase, at least one number" using (outputRegister.password.length>=6)
//                "Password must contain at least 6 characters with with at least one Uppercase, at least one number" using (outputRegister.password.)
        }

            is Commands.Preorder -> requireThat{

            }

            is Commands.Transfer -> requireThat {

            }

            is Commands.Issue -> requireThat {

            }
            is Commands.Move -> requireThat {

            }

        }

    }

}