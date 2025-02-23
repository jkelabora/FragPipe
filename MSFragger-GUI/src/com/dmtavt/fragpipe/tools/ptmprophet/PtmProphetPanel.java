/*
 * This file is part of FragPipe.
 *
 * FragPipe is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FragPipe is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FragPipe. If not, see <https://www.gnu.org/licenses/>.
 */

package com.dmtavt.fragpipe.tools.ptmprophet;

import com.dmtavt.fragpipe.Fragpipe;
import com.dmtavt.fragpipe.tabs.TabMsfragger;
import com.github.chhh.utils.SwingUtils;
import com.github.chhh.utils.swing.FormEntry;
import com.github.chhh.utils.swing.JPanelBase;
import com.github.chhh.utils.swing.MigUtils;
import com.github.chhh.utils.swing.UiCheck;
import com.github.chhh.utils.swing.UiText;
import com.github.chhh.utils.swing.UiUtils;
import java.awt.Component;
import java.awt.ItemSelectable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PtmProphetPanel extends JPanelBase {
  private static final Logger log = LoggerFactory.getLogger(PtmProphetPanel.class);
  private static final String PREFIX = "ptmprophet.";
  private JPanel pTop;
  private JPanel pContent;
  private static final MigUtils mu = MigUtils.get();
  private UiCheck checkRun;
  private UiText uiTextCmd;

  public PtmProphetPanel() {
    super();
  }

  @Override
  protected ItemSelectable getRunCheckbox() {
    return checkRun;
  }

  @Override
  protected Component getEnablementToggleComponent() {
    return pContent;
  }

  @Override
  protected String getComponentNamePrefix() {
    return PREFIX;
  }

  public void setRunStatus(boolean status) {
    checkRun.setEnabled(status);
  }

  @Override
  protected void init() {
    mu.layout(this, mu.lcFillXNoInsetsTopBottom());
    mu.border(this, "PTM Site Localization");

    pTop = createPanelTop();
    pContent = createPanelContent();

    mu.add(this, pTop).growX().wrap();
    mu.add(this, pContent).growX().wrap();
  }

  private JPanel createPanelContent() {
    JPanel p = mu.newPanel(null, mu.lcNoInsetsTopBottom());
    //mu.border(p, "PTMProphet options");

    //mu.add(p, new JLabel("Some dummy property")).wrap();

    uiTextCmd = UiUtils.uiTextBuilder().text(getDefaults()).create();
    FormEntry feCmdLineOpts = mu.feb("cmdline", uiTextCmd).label("Cmd line opts")
        .tooltip("Command line options for PTMProphet").create();

    mu.add(p, feCmdLineOpts.label(), mu.ccR());
    mu.add(p, feCmdLineOpts.comp).spanX().growX().pushX().wrap();

    return p;
  }

  private String getDefaults() {
    final String prop = "ptmprophet.cmdline.default";
    String val = Fragpipe.getPropFix(prop);
    if (val == null) {
      val = "KEEPOLD STATIC EM=1 NIONS=b STY:79.966331,M:15.9949 MINPROB=0.5";
      log.warn("Property [{}] not found in Bundle.properties, default to hardcoded value: {}", prop, val);
    }
    return val;
  }

  private JPanel createPanelTop() {

    JPanel p = mu.newPanel(null, mu.lcFillXNoInsetsTopBottom());

    checkRun = new UiCheck("Run PTMProphet", null, false);
    checkRun.setName("run-ptmprophet");
    JButton btnDefaults = UiUtils.createButton("Load defaults", e -> {
      uiTextCmd.setText(getDefaults());
    });
    JLabel info = new JLabel(
        "<html>Not for open searches. Mods format example: STY:79.966331,M:15.9949");

    checkRun.addActionListener(e -> {
      if (isRun()) {
        TabMsfragger tabMsfragger = Fragpipe.getStickyStrict(TabMsfragger.class);
        tabMsfragger.setWriteCalMzml(true); // If mass calibration is enabled, let MSFragger write calibrated mzML. If mass calibration is not enable, the calibrated mzML is till not generated.
      }
    });

    mu.add(p, checkRun).split();
    mu.add(p, btnDefaults).gapLeft("20px");
    mu.add(p, info).gapLeft("80px").wrap();
    return p;
  }

  public boolean isRun() {
    return SwingUtils.isEnabledAndChecked(checkRun);
  }

  public String getCmdLineOpts() {
    return uiTextCmd.getNonGhostText();
  }

  @Override
  public void initMore() {
    updateEnabledStatus(this, true);
    super.initMore();
  }
}
