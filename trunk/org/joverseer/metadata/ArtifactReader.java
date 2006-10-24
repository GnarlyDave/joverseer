package org.joverseer.metadata;

import org.joverseer.metadata.domain.Artifact;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: mskounak
 * Date: 24 ��� 2006
 * Time: 11:34:51 ��
 * To change this template use File | Settings | File Templates.
 */
public class ArtifactReader implements MetadataReader {
    String artifactFilename;

    public String getArtifactFilename() {
        return artifactFilename;
    }

    public void setArtifactFilename(String artifactFilename) {
        this.artifactFilename = artifactFilename;
    }

    public void load(GameMetadata gm) {
        gm.setArtifacts(loadArtifacts());
    }

    private HashMap loadArtifacts() {
        HashMap artifacts = new HashMap();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(getArtifactFilename()));
            String ln;
            while ((ln = reader.readLine()) != null) {
                String[] parts = ln.split(";");
                int no = Integer.parseInt(parts[0]);
                String name = parts[1];
                String power1 = parts[3];
                String bonus = parts[4];
                power1 += " " + bonus;
                String owner = (parts.length == 7 ? parts[6] : "");
                String alignment = parts[2];
                String power2 = (parts.length >= 6 ? parts[5] : "");
                Artifact artifact = new Artifact();
                artifact.setNo(no);
                artifact.setName(name);
                artifact.setOwner(owner);
                artifact.setAlignment(alignment);
                artifact.getPowers().add(power1);
                if (!power2.equals("")) {
                    artifact.getPowers().add(power2);
                }
                artifacts.put(artifact.getNo(), artifact);
            }
        }
        catch (IOException exc) {
            // todo see
            // do nothing
            int a = 1;
        }
        return artifacts;
    }
}
