package com.contentful.discovery.loaders;

import com.contentful.discovery.api.CFClient;
import com.contentful.java.cda.model.CDAArray;
import com.contentful.java.cda.model.CDAAsset;
import com.contentful.java.cda.model.CDAResource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import retrofit.RetrofitError;

/**
 * Assets Loader.
 * Use to load all CDA {@code Asset}s from the current {@code Space}.
 */
public class AssetsLoader extends AbsAsyncTaskLoader<ArrayList<CDAAsset>> {
  private static Gson gson;

  public AssetsLoader() {
    super();

    if (gson == null) {
      gson = new GsonBuilder().create();
    }
  }

  @Override protected ArrayList<CDAAsset> performLoad() {
    try {
      CDAArray cdaArray = CFClient.getClient().assets().fetchAll();
      ArrayList<CDAAsset> tmp = new ArrayList<>();

      for (CDAResource res : cdaArray.getItems()) {
        if (res instanceof CDAAsset) {
          tmp.add((CDAAsset) res);
        }
      }

      return tmp;
    } catch (RetrofitError e) {
      e.printStackTrace();
    }

    return null;
  }
}
