/**
 * 
 */
package org.joverseer.ui.command;

import org.joverseer.joApplication;
import org.joverseer.domain.FortificationSizeEnum;
import org.joverseer.domain.HarborSizeEnum;
import org.joverseer.domain.InformationSourceEnum;
import org.joverseer.domain.PopulationCenter;
import org.joverseer.domain.PopulationCenterSizeEnum;
import org.joverseer.game.Game;
import org.joverseer.game.TurnElementsEnum;
import org.joverseer.support.GameHolder;
import org.joverseer.support.infoSources.InfoSource;
import org.joverseer.support.infoSources.UserInfoSource;
import org.joverseer.ui.LifecycleEventsEnum;
import org.joverseer.ui.support.Messages;
import org.joverseer.ui.support.dialogs.ErrorDialog;
import org.joverseer.ui.views.EditPopulationCenterForm;
import org.springframework.binding.form.FormModel;
import org.springframework.richclient.command.ActionCommand;
import org.springframework.richclient.dialog.FormBackedDialogPage;
import org.springframework.richclient.dialog.TitledPageApplicationDialog;
import org.springframework.richclient.form.FormModelHelper;

public class AddPopCenterCommand extends ActionCommand {
	int hexNo;

	public AddPopCenterCommand(int hexNo) {
		super();
		this.hexNo = hexNo;
	}

	@Override
	protected void doExecuteCommand() {
		final PopulationCenter pc = new PopulationCenter();
		InfoSource is = new UserInfoSource();
		Game g = GameHolder.instance().getGame();
		if (g.getTurn().getContainer(TurnElementsEnum.PopulationCenter).findFirstByProperty("hexNo", this.hexNo) != null) { //$NON-NLS-1$
			ErrorDialog.showErrorDialog("AddPopCenterCommand.1"); //$NON-NLS-1$
			return;
		}
		is.setTurnNo(g.getCurrentTurn());
		pc.setInfoSource(is);
		pc.setInformationSource(InformationSourceEnum.exhaustive);
		pc.setHexNo(this.hexNo);
		pc.setSize(PopulationCenterSizeEnum.ruins);
		pc.setFortification(FortificationSizeEnum.none);
		pc.setHarbor(HarborSizeEnum.none);
		pc.setNationNo(0);
		pc.setLoyalty(0);
		pc.setName("-"); //$NON-NLS-1$
		FormModel formModel = FormModelHelper.createFormModel(pc);
		final EditPopulationCenterForm form = new EditPopulationCenterForm(formModel);
		FormBackedDialogPage page = new FormBackedDialogPage(form);

		TitledPageApplicationDialog dialog = new TitledPageApplicationDialog(page) {

			@Override
			protected void onAboutToShow() {
			}

			@Override
			protected boolean onFinish() {
				form.commit();
				GameHolder.instance().getGame().getTurn().getContainer(TurnElementsEnum.PopulationCenter).addItem(pc);
				joApplication.publishEvent(LifecycleEventsEnum.SelectedTurnChangedEvent, this, this);
				return true;
			}
		};
		dialog.setTitle(Messages.getString("editPopulationCenterDialog.title", new Object[] { String.valueOf(pc.getHexNo()) }));
		dialog.showDialog();
	}
}