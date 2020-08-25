/**
 * This package contains all extended modules.
 *
 * Some classes had be copied, because main realizations could not be changed
 * due the server changes.
 *
 * The following modules was extended:
 *      1. Mediator (class for linking figures with board fields):
 *          1) Now it contains extended game figures inside;
 *          2) Also the figures factory was changed for creating new realizations.
 *      2. Figures (chess figures)
 *          1) The figures were set for the new game prices:
 *              - Pawn: 1 -> 100
 *              - Knight: 3 -> 320
 *              - Bishop: 3 -> 330
 *              - Rook: 5 -> 500
 *              - Queen: 8 -> 900
 *              - King: Short.MAX_VALUE -> 0
 *      3. PossibleActionList (estimation of all possible attack and move figure fields)
 *          1) According to LVA/MVV heuristic the creation logic of possible action list
 *          was changed.
 */
package neointernship.web.client.player.bot.ai.extended;